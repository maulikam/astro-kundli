package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.Aspect;
import co.renil.astro.kundli.astrology.model.AspectType;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class AspectCalculation {

    /**
     * Determines if two planets form an aspect, and returns the Aspect details.
     *
     * @param planet1Name  Name of the first planet.
     * @param longitude1   Longitude of the first planet.
     * @param planet2Name  Name of the second planet.
     * @param longitude2   Longitude of the second planet.
     * @return An Aspect object if the planets form an aspect, null otherwise.
     */
    public static Aspect getAspect(String planet1Name, double longitude1, String planet2Name, double longitude2) {
        double angle = Math.abs(longitude1 - longitude2);
        angle = Math.min(angle, DEGREES_IN_CIRCLE - angle);

        for (AspectType aspectType : AspectType.values()) {
            if (isWithinOrb(angle, aspectType.getAngle(), aspectType.getOrb())) {
                return new Aspect(aspectType.getName(), angle, aspectType.getOrb(), planet1Name, planet2Name);
            }
        }
        return null;
    }

    /**
     * Calculates special aspects for Jupiter, Mars, and Saturn.
     *
     * @param planet      Name of the planet for which special aspects are calculated.
     * @param longitude1  Longitude of the first planet.
     * @param planet2Name Name of the second planet.
     * @param longitude2  Longitude of the second planet.
     * @return An Aspect object representing a special aspect, or null if no special aspect is found.
     */
    public static Aspect getSpecialAspect(String planet, double longitude1, String planet2Name, double longitude2) {
        double angle = Math.abs(longitude1 - longitude2);
        angle = Math.min(angle, DEGREES_IN_CIRCLE - angle);

        switch (planet.toLowerCase()) {
            case "jupiter":
                for (int house : JUPITER_ASPECTS) {
                    if (isWithinOrb(angle, house * DEGREES_PER_SIGN, CONJUNCTION_ORB)) {
                        return new Aspect("Jupiter Special", angle, CONJUNCTION_ORB, planet, planet2Name);
                    }
                }
                break;
            case "mars":
                for (int house : MARS_ASPECTS) {
                    if (isWithinOrb(angle, house * DEGREES_PER_SIGN, CONJUNCTION_ORB)) {
                        return new Aspect("Mars Special", angle, CONJUNCTION_ORB, planet, planet2Name);
                    }
                }
                break;
            case "saturn":
                for (int house : SATURN_ASPECTS) {
                    if (isWithinOrb(angle, house * DEGREES_PER_SIGN, CONJUNCTION_ORB)) {
                        return new Aspect("Saturn Special", angle, CONJUNCTION_ORB, planet, planet2Name);
                    }
                }
                break;
        }
        return null;
    }

    /**
     * Helper method to determine if the angle between two planets is within the orb of a given aspect.
     *
     * @param angle       The calculated angle between two planets.
     * @param aspectAngle The ideal angle for the aspect.
     * @param orb         The allowable deviation (orb) from the exact angle.
     * @return True if the angle is within the orb; otherwise, false.
     */
    private static boolean isWithinOrb(double angle, double aspectAngle, double orb) {
        return Math.abs(angle - aspectAngle) <= orb;
    }
}
