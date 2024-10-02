package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.Planet;
import co.renil.astro.kundli.astrology.util.AstrologyUtils;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class PlanetaryCalculation {

    /**
     * Calculates the position of a planet (longitude) based on Julian day.
     * This implementation is a placeholder; in real scenarios, precise ephemeris data is required.
     *
     * @param planetName The name of the planet.
     * @param julianDay The Julian day for the calculation.
     * @return The longitude of the planet in degrees.
     */
    public static double calculatePlanetPosition(String planetName, double julianDay) {
        double meanLongitude = 0.0;

        switch (planetName.toLowerCase()) {
            case "sun":
                meanLongitude = (SUN_MEAN_LONGITUDE + (julianDay - J2000) * (360 / TROPICAL_YEAR)) % 360;
                break;
            case "moon":
                meanLongitude = (julianDay * (360 / MOON_ORBITAL_PERIOD)) % 360;
                break;
            case "mercury":
                meanLongitude = (julianDay * (360 / MERCURY_ORBITAL_PERIOD)) % 360;
                break;
            case "venus":
                meanLongitude = (julianDay * (360 / VENUS_ORBITAL_PERIOD)) % 360;
                break;
            case "mars":
                meanLongitude = (julianDay * (360 / MARS_ORBITAL_PERIOD)) % 360;
                break;
            case "jupiter":
                meanLongitude = (julianDay * (360 / JUPITER_ORBITAL_PERIOD)) % 360;
                break;
            case "saturn":
                meanLongitude = (julianDay * (360 / SATURN_ORBITAL_PERIOD)) % 360;
                break;
            case "uranus":
                meanLongitude = (julianDay * (360 / URANUS_ORBITAL_PERIOD)) % 360;
                break;
            case "neptune":
                meanLongitude = (julianDay * (360 / NEPTUNE_ORBITAL_PERIOD)) % 360;
                break;
            case "rahu":
            case "ketu":
                // Rahu and Ketu move retrograde, and we approximate using mean motion.
                meanLongitude = (julianDay * RAHU_KETU_SPEED_DEG_PER_DAY) % 360;
                meanLongitude = (meanLongitude < 0) ? (360 + meanLongitude) : meanLongitude;
                break;
            default:
                throw new IllegalArgumentException("Unknown planet: " + planetName);
        }

        return meanLongitude;
    }

    /**
     * Calculates the speed of a planet at a given Julian day.
     * This implementation provides a rough estimate of the average speed for each planet.
     *
     * @param planetName The name of the planet.
     * @param julianDay  The Julian day for the calculation.
     * @return The speed of the planet in degrees per day.
     */
    public static double getPlanetSpeed(String planetName, double julianDay) {
        double speed;

        switch (planetName.toLowerCase()) {
            case "mercury":
                speed = MERCURY_MEAN_SPEED;
                break;
            case "venus":
                speed = VENUS_MEAN_SPEED;
                break;
            case "earth":
            case "sun": // The Sun's apparent speed
                speed = 0.9856; // Average apparent motion of the Sun in degrees per day
                break;
            case "mars":
                speed = MARS_MEAN_SPEED;
                break;
            case "jupiter":
                speed = JUPITER_MEAN_SPEED;
                break;
            case "saturn":
                speed = SATURN_MEAN_SPEED;
                break;
            case "uranus":
                speed = URANUS_MEAN_SPEED;
                break;
            case "neptune":
                speed = NEPTUNE_MEAN_SPEED;
                break;
            case "moon":
                speed = 13.176; // Average speed of the Moon in degrees per day
                break;
            case "rahu":
            case "ketu":
                speed = RAHU_KETU_SPEED_DEG_PER_DAY;
                break;
            default:
                throw new IllegalArgumentException("Unknown planet: " + planetName);
        }

        return speed;
    }

    /**
     * Calculates the dignity of a planet based on its position.
     */
    /**
     * Calculates the dignity of a planet based on its position.
     */
    public static void calculateDignity(Planet planet) {
        double longitude = planet.getLongitude();
        int signIndex = (int) (longitude / DEGREES_PER_SIGN);
        String sign = ZODIAC_SIGN_NAMES[signIndex];
        double degreeInSign = longitude % DEGREES_PER_SIGN;

        switch (planet.getName().toLowerCase()) {
            case "sun":
                if (sign.equals("Aries") && degreeInSign >= 10 && degreeInSign <= 20) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Libra")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "moon":
                if (sign.equals("Taurus") && degreeInSign >= 0 && degreeInSign <= 30) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Scorpio")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "mars":
                if (sign.equals("Capricorn") && degreeInSign >= 0 && degreeInSign <= 28) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Cancer")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "mercury":
                if (sign.equals("Virgo") && degreeInSign >= 0 && degreeInSign <= 15) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Pisces")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "jupiter":
                if (sign.equals("Cancer") && degreeInSign >= 5 && degreeInSign <= 10) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Capricorn")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "venus":
                if (sign.equals("Pisces") && degreeInSign >= 25 && degreeInSign <= 30) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Virgo")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "saturn":
                if (sign.equals("Libra") && degreeInSign >= 15 && degreeInSign <= 20) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Aries")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "rahu":
                if (sign.equals("Taurus") || sign.equals("Gemini")) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Scorpio") || sign.equals("Sagittarius")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            case "ketu":
                if (sign.equals("Scorpio") || sign.equals("Sagittarius")) {
                    planet.setDignity("Exalted");
                } else if (sign.equals("Taurus") || sign.equals("Gemini")) {
                    planet.setDignity("Debilitated");
                } else {
                    planet.setDignity("Normal");
                }
                break;
            default:
                planet.setDignity("Normal");
                break;
        }
    }

    /**
     * Determines if the planet is retrograde based on speed.
     */
    public static boolean isRetrograde(double speed) {
        return speed < 0;
    }

    /**
     * Calculates Shadbala (strength of planets).
     * This implementation provides a simplified version of Shadbala calculation.
     *
     * @param planet The planet whose Shadbala is to be calculated.
     * @param ascendant The ascendant degree.
     * @return The Shadbala value.
     */
    public static double calculateShadbala(LocalDateTime birthDateTime, double latitude, double longitude, Planet planet, double ascendant) {
        double shadbala = 0.0;

        // 1. Sthana Bala (Positional Strength)
        shadbala += calculateSthanaBala(planet, ascendant);

        // 2. Dig Bala (Directional Strength)
        shadbala += calculateDigBala(planet, ascendant);

        // 3. Kala Bala (Temporal Strength)
        shadbala += calculateKalaBala(birthDateTime, latitude, longitude, planet);

        // 4. Chesta Bala (Motional Strength)
        shadbala += calculateChestaBala(planet);

        // 5. Naisargika Bala (Natural Strength)
        shadbala += calculateNaisargikaBala(planet);

        // 6. Drik Bala (Aspectual Strength)
        shadbala += calculateDrikBala(planet);

        return shadbala;
    }

    private static double calculateSthanaBala(Planet planet, double ascendant) {
        double sthanaBala = 0.0;
        double longitude = planet.getLongitude();
        int signIndex = (int) (longitude / 30);

        // Exaltation strength
        if (planet.getDignity().equals("Exalted")) {
            sthanaBala += 1.0;
        } else if (planet.getDignity().equals("Debilitated")) {
            sthanaBala += 0.0;
        } else {
            // Own sign strength
            if (isOwnSign(planet, signIndex)) {
                sthanaBala += 0.75;
            } else if (isFriendSign(planet, signIndex)) {
                sthanaBala += 0.5;
            } else if (isNeutralSign(planet, signIndex)) {
                sthanaBala += 0.25;
            }
        }

        // Moolatrikona strength (simplified)
        if (isMoolatrikona(planet, longitude)) {
            sthanaBala += 0.75;
        }

        return sthanaBala;
    }

    private static double calculateDigBala(Planet planet, double ascendant) {
        double longitude = planet.getLongitude();
        int house = calculateHouse(longitude, ascendant);

        return determineDigBala(planet, house);
    }

    /**
     * Calculates the house number based on the planet's longitude and the ascendant.
     *
     * @param longitude The longitude of the planet.
     * @param ascendant The degree of the ascendant.
     * @return The house number (1-12).
     */
    private static int calculateHouse(double longitude, double ascendant) {
        return (int) ((longitude - ascendant + DEGREES_IN_CIRCLE) % DEGREES_IN_CIRCLE) / 30 + 1;
    }

    /**
     * Determines the Dig Bala for a planet based on its position in a house.
     *
     * @param planet The planet whose Dig Bala is to be determined.
     * @param house  The house number (1-12).
     * @return The Dig Bala value.
     */
    private static double determineDigBala(Planet planet, int house) {
        String planetName = planet.getName().toLowerCase();

        if ("sun".equals(planetName) || "mars".equals(planetName)) {
            return getDigBalaForSunAndMars(house);
        } else if ("moon".equals(planetName) || "venus".equals(planetName)) {
            return getDigBalaForMoonAndVenus(house);
        } else if ("jupiter".equals(planetName) || "mercury".equals(planetName)) {
            return getDigBalaForJupiterAndMercury(house);
        } else if ("saturn".equals(planetName)) {
            return getDigBalaForSaturn(house);
        } else {
            return 0.5; // Default for Rahu and Ketu
        }
    }

    /**
     * Returns the Dig Bala for Sun and Mars based on the house position.
     *
     * @param house The house number (1-12).
     * @return The Dig Bala value.
     */
    private static double getDigBalaForSunAndMars(int house) {
        if (house == 10) {
            return 1.0;
        } else if (house == 4) {
            return 0.0;
        } else {
            return 0.5;
        }
    }

    /**
     * Returns the Dig Bala for Moon and Venus based on the house position.
     *
     * @param house The house number (1-12).
     * @return The Dig Bala value.
     */
    private static double getDigBalaForMoonAndVenus(int house) {
        if (house == 4) {
            return 1.0;
        } else if (house == 10) {
            return 0.0;
        } else {
            return 0.5;
        }
    }

    /**
     * Returns the Dig Bala for Jupiter and Mercury based on the house position.
     *
     * @param house The house number (1-12).
     * @return The Dig Bala value.
     */
    private static double getDigBalaForJupiterAndMercury(int house) {
        if (house == 1 || house == 7) {
            return 1.0;
        } else if (house == 4 || house == 10) {
            return 0.0;
        } else {
            return 0.5;
        }
    }

    /**
     * Returns the Dig Bala for Saturn based on the house position.
     *
     * @param house The house number (1-12).
     * @return The Dig Bala value.
     */
    private static double getDigBalaForSaturn(int house) {
        if (house == 7) {
            return 1.0;
        } else if (house == 1) {
            return 0.0;
        } else {
            return 0.5;
        }
    }


    private static double calculateKalaBala(LocalDateTime birthDateTime, double latitude, double longitude, Planet planet) {
        double kalaBala = 0.0;

        // Day/Night strength (simplified)
        boolean isDaytime = isDaytime(birthDateTime, latitude, longitude); // Implement this method
        if ((isDaytime && isDayPlanet(planet)) || (!isDaytime && !isDayPlanet(planet))) {
            kalaBala += 0.5;
        }

        // Paksha Bala (for Moon)
        if (planet.getName().equalsIgnoreCase("moon")) {
            double moonPhase = calculateMoonPhase(); // Implement this method
            kalaBala += (moonPhase < 180) ? moonPhase / 180 : (360 - moonPhase) / 180;
        }

        // Tribhaga Bala (simplified)
        kalaBala += calculateTribhagaBala(planet);

        // Varsha Bala (yearly strength)
        kalaBala += calculateVarshaBala(planet);

        // Masa Bala (monthly strength)
        kalaBala += calculateMasaBala(planet);

        // Vara Bala (weekday strength)
        kalaBala += calculateVaraBala(planet);

        // Hora Bala (hourly strength)
        kalaBala += calculateHoraBala(planet);

        return kalaBala;
    }

    private static double calculateChestaBala(Planet planet) {
        if (planet.getName().equalsIgnoreCase("sun") || planet.getName().equalsIgnoreCase("moon")) {
            return 0.5; // Sun and Moon don't have Chesta Bala
        }

        double speed = planet.getSpeed();
        double maxSpeed = getMaxSpeed(planet); // Implement this method

        if (speed < 0) {
            // Retrograde motion
            return 0.25 + (Math.abs(speed) / maxSpeed) * 0.75;
        } else {
            // Direct motion
            return 0.5 + (speed / maxSpeed) * 0.5;
        }
    }

    private static double calculateNaisargikaBala(Planet planet) {
        switch (planet.getName().toLowerCase()) {
            case "sun": return 60.0 / 390.0;
            case "moon": return 51.43 / 390.0;
            case "mars": return 42.86 / 390.0;
            case "mercury": return 68.57 / 390.0;
            case "jupiter": return 77.14 / 390.0;
            case "venus": return 34.29 / 390.0;
            case "saturn": return 25.71 / 390.0;
            case "rahu":
            case "ketu": return 17.14 / 390.0;
            default: return 0.0;
        }
    }

    private static double calculateDrikBala(Planet planet) {
        // This is a simplified implementation. In a real scenario, you'd need to consider
        // aspects from all other planets and their strengths.
        double drikBala = 0.0;
        for (Planet otherPlanet : getAllPlanets()) {
            if (!otherPlanet.equals(planet)) {
                double aspect = calculateAspectStrength(planet, otherPlanet);
                drikBala += aspect;
            }
        }
        return Math.min(drikBala, DRIKBALA_MAX);
    }

    // Helper methods (to be implemented)
    private static boolean isOwnSign(Planet planet, int signIndex) {
        String planetName = planet.getName().toLowerCase();
        String signLord = ZODIAC_SIGN_LORDS[signIndex].toLowerCase();
        return planetName.equals(signLord);
    }

    private static boolean isFriendSign(Planet planet, int signIndex) {
        String planetName = planet.getName().toLowerCase();
        String signLord = ZODIAC_SIGN_LORDS[signIndex].toLowerCase();

        // This is a simplified friendship chart. In reality, it's more complex.
        switch (planetName) {
            case "sun":
                return signLord.equals("moon") || signLord.equals("mars") || signLord.equals("jupiter");
            case "moon":
                return signLord.equals("sun") || signLord.equals("mercury");
            // ... implement for other planets
            default:
                return false;
        }
    }

    private static boolean isNeutralSign(Planet planet, int signIndex) {
        return !isOwnSign(planet, signIndex) && !isFriendSign(planet, signIndex);
    }

    private static boolean isMoolatrikona(Planet planet, double longitude) {
        String planetName = planet.getName().toLowerCase();
        int signIndex = (int) (longitude / DEGREES_PER_SIGN);
        double degreeInSign = longitude % DEGREES_PER_SIGN;

        // Moolatrikona degrees for each planet
        switch (planetName) {
            case "sun":
                return signIndex == 4 && degreeInSign >= 0 && degreeInSign < 20; // Leo 0-19°59'
            case "moon":
                return signIndex == 1 && degreeInSign >= 3 && degreeInSign < 30; // Taurus 3-29°59'
            case "mars":
                return signIndex == 0 && degreeInSign >= 0 && degreeInSign < 12; // Aries 0-11°59'
            case "mercury":
                return signIndex == 5 && degreeInSign >= 15 && degreeInSign < 20; // Virgo 15-19°59'
            case "jupiter":
                return signIndex == 8 && degreeInSign >= 0 && degreeInSign < 10; // Sagittarius 0-9°59'
            case "venus":
                return signIndex == 6 && degreeInSign >= 0 && degreeInSign < 15; // Libra 0-14°59'
            case "saturn":
                return signIndex == 10 && degreeInSign >= 0 && degreeInSign < 20; // Aquarius 0-19°59'
            case "rahu":
            case "ketu":
                return false; // Rahu and Ketu don't have Moolatrikona
            default:
                return false;
        }
    }


    private static boolean isDaytime(LocalDateTime birthDateTime, double latitude, double longitude) {
        // Calculate sunrise and sunset times
        double julianDay = AstrologyUtils.calculateJulianDay(birthDateTime);
        double[] sunriseSunset = calculateSunriseSunset(julianDay, latitude, longitude);

        // Extract hour and minute from birthDateTime
        int birthHour = birthDateTime.getHour();
        int birthMinute = birthDateTime.getMinute();
        double birthTimeDecimal = birthHour + birthMinute / 60.0;

        // Check if birth time is between sunrise and sunset
        return birthTimeDecimal >= sunriseSunset[0] && birthTimeDecimal < sunriseSunset[1];
    }

    private static double[] calculateSunriseSunset(double julianDay, double latitude, double longitude) {
        // This is a simplified calculation. For more accuracy, use a comprehensive astronomical library.
        double n = julianDay - 2451545.0 + 0.0008;
        double jStar = n - longitude / 360.0;
        double m = (357.5291 + 0.98560028 * jStar) % 360;
        double l = (280.46646 + 0.98564736 * jStar + 1.915 * Math.sin(Math.toRadians(m)) + 0.020 * Math.sin(Math.toRadians(2 * m))) % 360;
        double d = Math.asin(Math.sin(Math.toRadians(23.439)) * Math.sin(Math.toRadians(l)));

        double cosOmega = (Math.sin(Math.toRadians(-0.83)) - Math.sin(Math.toRadians(latitude)) * Math.sin(d)) /
                (Math.cos(Math.toRadians(latitude)) * Math.cos(d));

        if (cosOmega > 1 || cosOmega < -1) {
            // Sun never rises or never sets at this location on this date
            return new double[]{Double.NaN, Double.NaN};
        }

        double omega = Math.toDegrees(Math.acos(cosOmega));
        double sunriseHour = (360 - omega) / 15;
        double sunsetHour = (omega) / 15;

        // Adjust for time zone (simplified, doesn't account for DST)
        double timeZoneOffset = longitude / 15;
        sunriseHour = (sunriseHour + timeZoneOffset + 24) % 24;
        sunsetHour = (sunsetHour + timeZoneOffset + 24) % 24;

        return new double[]{sunriseHour, sunsetHour};
    }

    private static boolean isDayPlanet(Planet planet) {
        String planetName = planet.getName().toLowerCase();
        return planetName.equals("sun") || planetName.equals("jupiter") || planetName.equals("saturn");
    }

    private static double calculateMoonPhase() {
        // This would typically require the positions of the Sun and Moon
        // For simplicity, we'll use a placeholder implementation
        return Math.random() * 360; // Returns a random phase between 0 and 360 degrees
    }

    private static double calculateTribhagaBala(Planet planet) {
        double longitude = planet.getLongitude();
        int signIndex = (int) (longitude / DEGREES_PER_SIGN);
        double degreeInSign = longitude % DEGREES_PER_SIGN;

        if (degreeInSign < 10) return 1.0; // First third of the sign
        if (degreeInSign < 20) return 0.5; // Second third of the sign
        return 0.25; // Last third of the sign
    }

    private static double calculateVarshaBala(Planet planet) {
        // This would typically require the current year and complex calculations
        // For simplicity, we'll use a placeholder implementation
        return Math.random(); // Returns a random value between 0 and 1
    }

    private static double calculateMasaBala(Planet planet) {
        // This would typically require the current month and complex calculations
        // For simplicity, we'll use a placeholder implementation
        return Math.random(); // Returns a random value between 0 and 1
    }

    private static double calculateVaraBala(Planet planet) {
        // This would typically require the day of the week and complex calculations
        // For simplicity, we'll use a placeholder implementation
        return Math.random(); // Returns a random value between 0 and 1
    }

    private static double calculateHoraBala(Planet planet) {
        // This would typically require the current hour and complex calculations
        // For simplicity, we'll use a placeholder implementation
        return Math.random(); // Returns a random value between 0 and 1
    }

    private static double getMaxSpeed(Planet planet) {
        String planetName = planet.getName().toLowerCase();
        switch (planetName) {
            case "mercury":
                return MERCURY_MEAN_SPEED;
            case "venus":
                return VENUS_MEAN_SPEED;
            case "mars":
                return MARS_MEAN_SPEED;
            case "jupiter":
                return JUPITER_MEAN_SPEED;
            case "saturn":
                return SATURN_MEAN_SPEED;
            case "uranus":
                return URANUS_MEAN_SPEED;
            case "neptune":
                return NEPTUNE_MEAN_SPEED;
            default:
                return 1.0; // Default value for Sun, Moon, Rahu, and Ketu
        }
    }

    private static List<Planet> getAllPlanets() {
        // This method should return a list of all planets in the chart
        // For simplicity, we'll return a placeholder list
        return Arrays.asList(
                new Planet("Sun", 0, 1),
                new Planet("Moon", 30, 13),
                new Planet("Mars", 60, 0.5),
                new Planet("Mercury", 90, 1.5),
                new Planet("Jupiter", 120, 0.1),
                new Planet("Venus", 150, 1.2),
                new Planet("Saturn", 180, 0.03)
        );
    }

    private static double calculateAspectStrength(Planet planet1, Planet planet2) {
        double angle = Math.abs(planet1.getLongitude() - planet2.getLongitude());
        angle = Math.min(angle, 360 - angle);

        if (isWithinOrb(angle, 0, CONJUNCTION_ORB)) return 1.0;
        if (isWithinOrb(angle, 60, SEXTILE_ORB)) return 0.5;
        if (isWithinOrb(angle, 90, SQUARE_ORB)) return -0.5;
        if (isWithinOrb(angle, 120, TRINE_ORB)) return 1.0;
        if (isWithinOrb(angle, 180, OPPOSITION_ORB)) return -1.0;

        return 0.0;
    }

    private static boolean isWithinOrb(double angle, double aspectAngle, double orb) {
        return Math.abs(angle - aspectAngle) <= orb;
    }

    /**
     * Determines if a planet is combusted (too close to the Sun).
     *
     * @param planet The planet being checked.
     * @param sunLongitude The longitude of the Sun.
     * @return True if the planet is combusted, false otherwise.
     */
    public static boolean isCombust(Planet planet, double sunLongitude) {
        double orb = (planet.getName().equalsIgnoreCase("venus") || planet.getName().equalsIgnoreCase("mars")) ? 10 : 6;
        return Math.abs(planet.getLongitude() - sunLongitude) <= orb;
    }




}