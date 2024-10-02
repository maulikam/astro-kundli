package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.Aspect;
import co.renil.astro.kundli.astrology.model.AspectType;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class AspectCalculation {

    /**
     * Determines if two planets form an aspect, and returns the Aspect details.
     */
    public static Aspect getAspect(double longitude1, double longitude2) {
        double angle = Math.abs(longitude1 - longitude2);
        angle = Math.min(angle, DEGREES_IN_CIRCLE - angle);

        for (AspectType aspectType : AspectType.values()) {
            if (isWithinOrb(angle, aspectType.getAngle(), aspectType.getOrb())) {
                return new Aspect(aspectType.getName(), angle, aspectType.getOrb());
            }
        }
        return null;
    }

    /**
     * Calculates special aspects for Jupiter, Mars, and Saturn.
     */
    public static Aspect getSpecialAspect(String planet, double longitude1, double longitude2) {
        double angle = Math.abs(longitude1 - longitude2);
        angle = Math.min(angle, DEGREES_IN_CIRCLE - angle);

        switch (planet.toLowerCase()) {
            case "jupiter":
                for (int house : JUPITER_ASPECTS) {
                    if (isWithinOrb(angle, house * DEGREES_PER_SIGN, CONJUNCTION_ORB)) {
                        return new Aspect("Jupiter Special", angle, CONJUNCTION_ORB);
                    }
                }
                break;
            case "mars":
                for (int house : MARS_ASPECTS) {
                    if (isWithinOrb(angle, house * DEGREES_PER_SIGN, CONJUNCTION_ORB)) {
                        return new Aspect("Mars Special", angle, CONJUNCTION_ORB);
                    }
                }
                break;
            case "saturn":
                for (int house : SATURN_ASPECTS) {
                    if (isWithinOrb(angle, house * DEGREES_PER_SIGN, CONJUNCTION_ORB)) {
                        return new Aspect("Saturn Special", angle, CONJUNCTION_ORB);
                    }
                }
                break;
        }
        return null;
    }

    private static boolean isWithinOrb(double angle, double aspectAngle, double orb) {
        return Math.abs(angle - aspectAngle) <= orb;
    }
}
