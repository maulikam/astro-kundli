package co.renil.astro.kundli.astrology.chart;

import co.renil.astro.kundli.astrology.calculation.*;
import co.renil.astro.kundli.astrology.model.*;
import co.renil.astro.kundli.astrology.util.AstrologyUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

@Service
public class BirthChartGenerator {

    private static final Logger logger = LoggerFactory.getLogger(BirthChartGenerator.class);

    public BirthChart generateBirthChart(LocalDateTime birthDateTime, double longitude, double latitude) {
        validateInputParameters(birthDateTime, longitude, latitude);

        logger.info("Generating birth chart for birth time: {}, longitude: {}, latitude: {}", birthDateTime, longitude, latitude);

        double julianDay = AstrologyUtils.calculateJulianDay(birthDateTime);
        double siderealTime = AstrologyUtils.calculateSiderealTime(julianDay) + (longitude * SIDEREAL_TIME_CONSTANT);
        double ayanamsa = AstrologyUtils.calculateAyanamsa(julianDay);

        BirthChart birthChart = new BirthChart();
        birthChart.setHouses(generateHouses(siderealTime));
        birthChart.setPlanets(generatePlanetaryPositions(julianDay, ayanamsa));
        birthChart.setZodiacSigns(generateZodiacSigns(birthChart.getPlanets()));
        birthChart.setAspects(generateAspects(birthChart.getPlanets()));
        birthChart.setNavamsa(generateNavamsa(birthChart.getPlanets()));
        birthChart.setDasha(generateDasha(birthChart.getPlanets(), birthDateTime));

        calculateAdditionalPlanetaryInfo(birthDateTime, latitude, longitude, birthChart);

        logger.info("Birth chart generation completed.");
        return birthChart;
    }

    private void validateInputParameters(LocalDateTime birthDateTime, double longitude, double latitude) {
        if (birthDateTime == null) {
            throw new IllegalArgumentException("Birth date and time must not be null.");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees.");
        }
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees.");
        }
    }

    private House[] generateHouses(double siderealTime) {
        logger.debug("Calculating houses using sidereal time: {}", siderealTime);
        return HouseCalculation.calculateEqualHouseSystem(siderealTime);
    }

    private List<Planet> generatePlanetaryPositions(double julianDay, double ayanamsa) {
        logger.debug("Calculating planetary positions for Julian Day: {}, Ayanamsa: {}", julianDay, ayanamsa);
        return NAVAGRAHA.stream()
                .map(planetName -> createPlanet(planetName, julianDay, ayanamsa))
                .collect(Collectors.toList());
    }

    private Planet createPlanet(String planetName, double julianDay, double ayanamsa) {
        double longitude = PlanetaryCalculation.calculatePlanetPosition(planetName, julianDay);
        longitude = AstrologyUtils.normalizeDegrees(longitude - ayanamsa);

        double speed = PlanetaryCalculation.getPlanetSpeed(planetName, julianDay);  // Corrected method name

        Planet planet = new Planet(planetName, longitude, speed);
        PlanetaryCalculation.calculateDignity(planet);
        planet.setRetrograde(PlanetaryCalculation.isRetrograde(speed));

        return planet;
    }


    private List<ZodiacSign> generateZodiacSigns(List<Planet> planets) {
        logger.debug("Determining zodiac signs for each planet.");
        return planets.stream()
                .map(planet -> ZodiacCalculation.getZodiacSign(planet.getLongitude()))
                .collect(Collectors.toList());
    }

    private List<Aspect> generateAspects(List<Planet> planets) {
        logger.debug("Calculating aspects between planets.");
        List<Aspect> aspects = new ArrayList<>();

        for (int i = 0; i < planets.size(); i++) {
            for (int j = i + 1; j < planets.size(); j++) {
                Planet planet1 = planets.get(i);
                Planet planet2 = planets.get(j);

                aspects.addAll(calculateAspectsForPlanetPair(planet1, planet2));
            }
        }

        return aspects;
    }

    private List<Aspect> calculateAspectsForPlanetPair(Planet planet1, Planet planet2) {
        List<Aspect> aspects = new ArrayList<>();
        double angle = Math.abs(planet1.getLongitude() - planet2.getLongitude());
        angle = Math.min(angle, DEGREES_IN_CIRCLE - angle);

        for (AspectType aspectType : AspectType.values()) {
            if (isWithinOrb(angle, aspectType.getAngle(), aspectType.getOrb())) {
                aspects.add(new Aspect(aspectType.getName(), angle, aspectType.getOrb(), planet1.getName(), planet2.getName()));
            }
        }

        Aspect specialAspect = AspectCalculation.getSpecialAspect(planet1.getName(), planet1.getLongitude(), planet1.getName(), planet2.getLongitude());
        if (specialAspect != null) {
            specialAspect.setPlanet1(planet1.getName());
            specialAspect.setPlanet2(planet2.getName());
            aspects.add(specialAspect);
        }

        return aspects;
    }

    private boolean isWithinOrb(double angle, double aspectAngle, double orb) {
        return Math.abs(angle - aspectAngle) <= orb;
    }

    private Map<String, Integer> generateNavamsa(List<Planet> planets) {
        logger.debug("Calculating Navamsa positions for planets.");
        return planets.stream()
                .collect(Collectors.toMap(
                        Planet::getName,
                        planet -> ZodiacCalculation.calculateNavamsa(planet.getLongitude())
                ));
    }

    private String generateDasha(List<Planet> planets, LocalDateTime birthDateTime) {
        logger.debug("Calculating Vimshottari Dasha.");
        return planets.stream()
                .filter(p -> p.getName().equalsIgnoreCase("Moon"))
                .findFirst()
                .map(moon -> DashaCalculation.calculateVimshottariDasha(moon.getLongitude(), birthDateTime, LocalDateTime.now()))
                .orElse("");
    }

    private void calculateAdditionalPlanetaryInfo(LocalDateTime birthDateTime, double latitude, double longitude, BirthChart birthChart) {
        logger.debug("Calculating additional planetary information.");
        List<Planet> planets = birthChart.getPlanets();
        Optional<Planet> sun = planets.stream().filter(p -> p.getName().equalsIgnoreCase("Sun")).findFirst();
        double ascendantDegree = birthChart.getHouses()[0].getCuspDegree();

        planets.forEach(planet -> updatePlanetInfo(birthDateTime, latitude, longitude, planet, sun, ascendantDegree));
    }

    private void updatePlanetInfo(LocalDateTime birthDateTime, double latitude, double longitude, Planet planet, Optional<Planet> sun, double ascendantDegree) {
        double shadbala = PlanetaryCalculation.calculateShadbala(birthDateTime, latitude, longitude, planet, ascendantDegree);
        planet.setShadbala(shadbala);

        sun.ifPresent(s -> {
            if (!planet.equals(s)) {
                boolean isCombust = PlanetaryCalculation.isCombust(planet, s.getLongitude());
                planet.setCombust(isCombust);
            }
        });
    }
}
