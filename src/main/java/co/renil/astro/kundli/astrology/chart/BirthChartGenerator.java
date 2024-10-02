package co.renil.astro.kundli.astrology.chart;

import co.renil.astro.kundli.astrology.calculation.*;
import co.renil.astro.kundli.astrology.model.*;
import co.renil.astro.kundli.astrology.util.AstrologyUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class BirthChartGenerator {

    private static final Logger logger = LoggerFactory.getLogger(BirthChartGenerator.class);

    /**
     * Generates a birth chart based on the given birth time and location details.
     *
     * @param birthDateTime The birth date and time.
     * @param longitude     The longitude of the birth location.
     * @param latitude      The latitude of the birth location.
     * @return The generated birth chart.
     */
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

        calculateAdditionalPlanetaryInfo(birthChart);

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
        List<Planet> planets = new ArrayList<>();

        // Loop through each planet in NAVAGRAHA (i.e., the main celestial bodies in Vedic astrology)
        for (String planetName : NAVAGRAHA) {
            // Step 1: Calculate the current position (longitude) of the planet
            double longitude = PlanetaryCalculation.calculatePlanetPosition(planetName, julianDay);

            // Step 2: Apply ayanamsa to get the sidereal position
            longitude = AstrologyUtils.normalizeDegrees(longitude - ayanamsa);

            // Step 3: Create a new Planet object and set the basic properties
            Planet planet = new Planet(planetName, longitude);

            // Step 4: Calculate the dignity of the planet (Exalted, Debilitated, etc.)
            PlanetaryCalculation.calculateDignity(planet);

            // Step 5: Determine if the planet is in retrograde motion
            double speed = PlanetaryCalculation.getPlanetSpeed(planetName, julianDay); // Assume this method returns the speed of the planet at the given time
            boolean isRetrograde = PlanetaryCalculation.isRetrograde(speed);
            planet.setRetrograde(isRetrograde);

            // Step 6: Calculate the Shadbala (strength) of the planet
            double shadbala = PlanetaryCalculation.calculateShadbala(planet, planets.size() > 0 ? planets.get(0).getLongitude() : 0); // Using first planet (usually Sun) or a placeholder value
            planet.setShadbala(shadbala);

            // Step 7: Add the newly created and updated planet object to the list
            planets.add(planet);
        }

        return planets;
    }


    private List<ZodiacSign> generateZodiacSigns(List<Planet> planets) {
        logger.debug("Determining zodiac signs for each planet.");
        List<ZodiacSign> zodiacSigns = new ArrayList<>();
        for (Planet planet : planets) {
            ZodiacSign zodiacSign = ZodiacCalculation.getZodiacSign(planet.getLongitude());
            zodiacSigns.add(zodiacSign);
        }
        return zodiacSigns;
    }

    private List<Aspect> generateAspects(List<Planet> planets) {
        logger.debug("Calculating aspects between planets.");
        List<Aspect> aspects = new ArrayList<>();

        // Loop through all unique pairs of planets
        for (int i = 0; i < planets.size(); i++) {
            for (int j = i + 1; j < planets.size(); j++) {
                Planet planet1 = planets.get(i);
                Planet planet2 = planets.get(j);

                double angle = Math.abs(planet1.getLongitude() - planet2.getLongitude());
                angle = Math.min(angle, DEGREES_IN_CIRCLE - angle);

                // Loop through each aspect type to find matching aspects
                for (AspectType aspectType : AspectType.values()) {
                    if (isWithinOrb(angle, aspectType.getAngle(), aspectType.getOrb())) {
                        aspects.add(new Aspect(aspectType.getName(), angle, aspectType.getOrb()));
                    }
                }

                // Calculate special aspects for planets like Jupiter, Mars, and Saturn
                Aspect specialAspect = AspectCalculation.getSpecialAspect(planet1.getName(), planet1.getLongitude(), planet2.getLongitude());
                if (specialAspect != null) {
                    aspects.add(specialAspect);
                }
            }
        }

        return aspects;
    }

    /**
     * Helper method to determine if the angle between two planets is within the orb of a given aspect.
     *
     * @param angle       The calculated angle between two planets.
     * @param aspectAngle The ideal angle for the aspect.
     * @param orb         The allowable deviation (orb) from the exact angle.
     * @return True if the angle is within the orb; otherwise, false.
     */
    private boolean isWithinOrb(double angle, double aspectAngle, double orb) {
        return Math.abs(angle - aspectAngle) <= orb;
    }


    private Map<String, Integer> generateNavamsa(List<Planet> planets) {
        logger.debug("Calculating Navamsa positions for planets.");
        Map<String, Integer> navamsa = new HashMap<>();
        for (Planet planet : planets) {
            int navamsaPosition = ZodiacCalculation.calculateNavamsa(planet.getLongitude());
            navamsa.put(planet.getName(), navamsaPosition);
        }
        return navamsa;
    }

    private String generateDasha(List<Planet> planets, LocalDateTime birthDateTime) {
        logger.debug("Calculating Vimshottari Dasha.");
        Planet moon = planets.stream().filter(p -> p.getName().equalsIgnoreCase("Moon")).findFirst().orElse(null);
        if (moon != null) {
            return DashaCalculation.calculateVimshottariDasha(moon.getLongitude(), birthDateTime, LocalDateTime.now());
        }
        logger.warn("Moon position not found, unable to calculate Dasha.");
        return "";
    }

    private void calculateAdditionalPlanetaryInfo(BirthChart birthChart) {
        logger.debug("Calculating additional planetary information such as Shadbala, combustion, and retrograde motion.");
        List<Planet> planets = birthChart.getPlanets();
        Planet sun = planets.stream().filter(p -> p.getName().equalsIgnoreCase("Sun")).findFirst().orElse(null);

        for (Planet planet : planets) {
            // Calculate Shadbala
            double shadbala = PlanetaryCalculation.calculateShadbala(planet, birthChart.getHouses()[0].getCuspDegree());
            planet.setShadbala(shadbala);

            // Check for combustion
            if (sun != null && !planet.equals(sun)) {
                boolean isCombust = PlanetaryCalculation.isCombust(planet, sun.getLongitude());
                planet.setCombust(isCombust);
            }

            // Calculate retrograde motion
            boolean isRetrograde = PlanetaryCalculation.isRetrograde(planet.getSpeed());
            planet.setRetrograde(isRetrograde);
        }
    }
}
