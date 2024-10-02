package co.renil.astro.kundli.astrology.calculation;

import co.renil.astro.kundli.astrology.model.House;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class HouseCalculation {

    /**
     * Calculates the house cusps using the Equal House system.
     */
    public static House[] calculateEqualHouseSystem(double ascendant) {
        House[] houses = new House[TOTAL_BHAVAS];
        for (int i = 0; i < TOTAL_BHAVAS; i++) {
            double cuspDegree = normalizeAngle(ascendant + (i * DEGREES_PER_SIGN));
            String bhavaName = BHAVA_NAMES[i];
            String ruler = getHouseRuler(i);
            houses[i] = new House(i + 1, cuspDegree, ruler, bhavaName);
        }
        return houses;
    }

    /**
     * Calculates Bhava Madhya (mid-point of houses)
     */
    public static double[] calculateBhavaMadhya(House[] houses) {
        double[] bhavaMadhya = new double[TOTAL_BHAVAS];
        for (int i = 0; i < TOTAL_BHAVAS; i++) {
            int nextIndex = (i + 1) % TOTAL_BHAVAS;
            bhavaMadhya[i] = normalizeAngle((houses[i].getCuspDegree() + houses[nextIndex].getCuspDegree()) / 2);
        }
        return bhavaMadhya;
    }

    private static String getHouseRuler(int houseNumber) {
        return ZODIAC_SIGN_LORDS[houseNumber % ZODIAC_SIGNS];
    }

    private static double normalizeAngle(double angle) {
        angle = angle % 360.0;
        if (angle < 0) {
            angle += 360.0;
        }
        return angle;
    }

}
