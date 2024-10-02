package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.ZodiacSign;

import java.util.Arrays;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class ZodiacCalculation {

    /**
     * Returns the Zodiac sign for a given longitude.
     */
    public static ZodiacSign getZodiacSign(double longitude) {
        int signIndex = (int) (longitude / DEGREES_PER_SIGN);
        String name = ZODIAC_SIGN_NAMES[signIndex];
        double startDegree = signIndex * DEGREES_PER_SIGN;
        double endDegree = startDegree + DEGREES_PER_SIGN;
        String rulingPlanet = ZODIAC_SIGN_LORDS[signIndex];
        return new ZodiacSign(name, startDegree, endDegree, rulingPlanet);
    }

    /**
     * Checks if the given degree falls under a particular zodiac sign.
     */
    public static boolean isWithinZodiacSign(double longitude, ZodiacSign zodiacSign) {
        return longitude >= zodiacSign.getStartDegree() && longitude < zodiacSign.getEndDegree();
    }

    /**
     * Calculates the Navamsa (D9 chart) position
     */
    public static int calculateNavamsa(double longitude) {
        double navamsaLongitude = (longitude % DEGREES_PER_SIGN) * 9;
        return (int) (navamsaLongitude / DEGREES_PER_SIGN);
    }

    /**
     * Determines the element (tattva) of a zodiac sign
     */
    public static String getElement(ZodiacSign sign) {
        int index = (Arrays.asList(ZODIAC_SIGN_NAMES).indexOf(sign.getName())) % 4;
        return ELEMENTS[index];
    }

}
