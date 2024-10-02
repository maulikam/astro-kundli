package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.Planet;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class PlanetaryCalculation {

    /**
     * Calculates the dignity of a planet based on its position.
     */
    public static void calculateDignity(Planet planet) {
        double longitude = planet.getLongitude();
        String sign = ZODIAC_SIGN_NAMES[(int) (longitude / DEGREES_PER_SIGN)];

        switch (planet.getName().toLowerCase()) {
            case "sun":
                planet.setDignity((longitude >= 120 && longitude < 150) ? "Exalted" : "Normal");
                break;
            case "moon":
                planet.setDignity((longitude >= 30 && longitude < 60) ? "Exalted" : "Normal");
                break;
            // Add more planets and specific calculations
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
     * Calculates Shadbala (strength of planets)
     */
    public static double calculateShadbala(Planet planet, double ascendant) {
        // Implement Shadbala calculation logic (Placeholder)
        return 0.0;
    }

    /**
     * Determines if a planet is combusted (too close to the Sun)
     */
    public static boolean isCombust(Planet planet, double sunLongitude) {
        double orb = (planet.getName().equalsIgnoreCase("venus") || planet.getName().equalsIgnoreCase("mars")) ? 10 : 6;
        return Math.abs(planet.getLongitude() - sunLongitude) <= orb;
    }

}
