package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.Planet;
import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

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
     * This implementation is a placeholder and should be further detailed for accuracy.
     *
     * @param planet The planet whose Shadbala is to be calculated.
     * @param ascendant The ascendant degree.
     * @return The Shadbala value.
     */
    public static double calculateShadbala(Planet planet, double ascendant) {
        // Implement Shadbala calculation logic (Placeholder)
        return 0.0;
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
