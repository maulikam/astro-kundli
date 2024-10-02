package co.renil.astro.kundli.astrology.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class AstrologyUtils {

    private AstrologyUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts degrees to radians.
     *
     * @param degrees The angle in degrees
     * @return The angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return degrees * DEG_TO_RAD;
    }

    /**
     * Converts radians to degrees.
     *
     * @param radians The angle in radians
     * @return The angle in degrees
     */
    public static double radiansToDegrees(double radians) {
        return radians * RAD_TO_DEG;
    }

    /**
     * Normalizes an angle to be between 0 and 360 degrees.
     *
     * @param angle The angle to normalize
     * @return The normalized angle
     */
    public static double normalizeDegrees(double angle) {
        angle = angle % 360.0;
        if (angle < 0) {
            angle += 360.0;
        }
        return angle;
    }

    /**
     * Calculates the Julian Day for a given date and time.
     *
     * @param dateTime The date and time
     * @return The Julian Day
     */
    public static double calculateJulianDay(LocalDateTime dateTime) {
        ZonedDateTime zdt = dateTime.atZone(ZoneId.of("UTC"));
        int year = zdt.getYear();
        int month = zdt.getMonthValue();
        int day = zdt.getDayOfMonth();
        double hours = zdt.getHour() + zdt.getMinute() / 60.0 + zdt.getSecond() / 3600.0;

        if (month <= 2) {
            year -= 1;
            month += 12;
        }

        int a = year / 100;
        int b = 2 - a + (a / 4);

        return Math.floor(365.25 * (year + 4716)) + Math.floor(30.6001 * (month + 1)) + day + b - 1524.5 + hours / 24.0;
    }

    /**
     * Calculates the sidereal time at Greenwich for a given Julian Day.
     *
     * @param jd The Julian Day
     * @return The sidereal time at Greenwich in degrees
     */
    public static double calculateSiderealTime(double jd) {
        double t = (jd - J2000) / JULIAN_CENTURY;
        double siderealTime = 280.46061837 + 360.98564736629 * (jd - J2000)
                + 0.000387933 * t * t - t * t * t / 38710000.0;
        return normalizeDegrees(siderealTime);
    }

    /**
     * Calculates the Ayanamsa (precession correction) for a given Julian Day.
     *
     * @param jd The Julian Day
     * @return The Ayanamsa in degrees
     */
    public static double calculateAyanamsa(double jd) {
        double t = (jd - J2000) / JULIAN_CENTURY;
        return AYANAMSA_2000 + AYANAMSA_ANNUAL_PRECESSION * t / 3600.0;
    }

    /**
     * Converts tropical longitude to sidereal longitude.
     *
     * @param tropicalLongitude The tropical longitude in degrees
     * @param jd                The Julian Day for Ayanamsa calculation
     * @return The sidereal longitude in degrees
     */
    public static double tropicalToSidereal(double tropicalLongitude, double jd) {
        double ayanamsa = calculateAyanamsa(jd);
        return normalizeDegrees(tropicalLongitude - ayanamsa);
    }

    /**
     * Calculates the aspect angle between two planets.
     *
     * @param longitude1 The longitude of the first planet in degrees
     * @param longitude2 The longitude of the second planet in degrees
     * @return The aspect angle in degrees
     */
    public static double calculateAspectAngle(double longitude1, double longitude2) {
        double aspect = Math.abs(longitude1 - longitude2);
        return normalizeDegrees(aspect);
    }

    /**
     * Determines if two planets are in aspect, considering the orb.
     *
     * @param angle       The angle between the planets
     * @param aspectAngle The ideal aspect angle
     * @param orb         The allowed orb for the aspect
     * @return true if the planets are in aspect, false otherwise
     */
    public static boolean isInAspect(double angle, double aspectAngle, double orb) {
        return Math.abs(angle - aspectAngle) <= orb;
    }

    /**
     * Calculates the house cusp for a given ascendant and house number using the Equal House system.
     *
     * @param ascendant   The ascendant degree
     * @param houseNumber The house number (1-12)
     * @return The house cusp degree
     */
    public static double calculateEqualHouseCusp(double ascendant, int houseNumber) {
        return normalizeDegrees(ascendant + (houseNumber - 1) * 30.0);
    }

    /**
     * Determines the zodiac sign for a given longitude.
     *
     * @param longitude The longitude in degrees
     * @return The index of the zodiac sign (0-11)
     */
    public static int calculateZodiacSign(double longitude) {
        return (int) (normalizeDegrees(longitude) / DEGREES_PER_SIGN);
    }

    /**
     * Calculates the Nakshatra (Lunar Mansion) for a given longitude.
     *
     * @param longitude The longitude in degrees
     * @return The index of the nakshatra (0-26)
     */
    public static int calculateNakshatra(double longitude) {
        return (int) (normalizeDegrees(longitude) / DEGREES_PER_NAKSHATRA);
    }

    /**
     * Calculates Rahu Kaal time for a given day based on sunrise and sunset.
     *
     * @param sunrise Time of sunrise in decimal hours
     * @param sunset  Time of sunset in decimal hours
     * @param weekday The weekday (0 for Sunday, 1 for Monday, etc.)
     * @return An array with the start and end times of Rahu Kaal in decimal hours
     */
    public static double[] calculateRahuKaal(double sunrise, double sunset, int weekday) {
        double dayDuration = sunset - sunrise;
        double segmentDuration = dayDuration / DAY_TIME_SEGMENTS;
        int rahuKaalSegment = switch (weekday) {
            case 0 -> 8; // Sunday
            case 1 -> 2; // Monday
            case 2 -> 7; // Tuesday
            case 3 -> 5; // Wednesday
            case 4 -> 6; // Thursday
            case 5 -> 4; // Friday
            case 6 -> 3; // Saturday
            default -> 8;
        };
        double start = sunrise + (rahuKaalSegment - 1) * segmentDuration;
        return new double[]{start, start + segmentDuration};
    }

    /**
     * Calculates the Moon's phase angle.
     *
     * @param moonLongitude The Moon's longitude in degrees
     * @param sunLongitude  The Sun's longitude in degrees
     * @return The Moon's phase angle in degrees
     */
    public static double calculateMoonPhase(double moonLongitude, double sunLongitude) {
        return normalizeDegrees(moonLongitude - sunLongitude);
    }

    /**
     * Determines if an eclipse (solar or lunar) is possible based on the proximity to Rahu/Ketu.
     *
     * @param sunLongitude  The Sun's longitude in degrees
     * @param moonLongitude The Moon's longitude in degrees
     * @param rahuLongitude The Rahu's longitude in degrees
     * @return True if an eclipse is possible, false otherwise
     */
    public static boolean isEclipsePossible(double sunLongitude, double moonLongitude, double rahuLongitude) {
        double solarDistanceFromNode = Math.abs(normalizeDegrees(sunLongitude - rahuLongitude));
        double lunarDistanceFromNode = Math.abs(normalizeDegrees(moonLongitude - rahuLongitude));
        return (solarDistanceFromNode < SOLAR_ECLIPSE_THRESHOLD_DEGREES || lunarDistanceFromNode < LUNAR_ECLIPSE_THRESHOLD_DEGREES);
    }

    /**
     * Calculates the tithi (lunar day) based on the Moon's phase.
     *
     * @param moonPhase The Moon's phase angle in degrees
     * @return The tithi number (1-30)
     */
    public static int calculateTithi(double moonPhase) {
        return (int) (moonPhase / DEGREES_PER_TITHI) + 1;
    }

    /**
     * Calculates the yoga based on the sum of the Sun's and Moon's longitudes.
     *
     * @param sunLongitude  The Sun's longitude in degrees
     * @param moonLongitude The Moon's longitude in degrees
     * @return The yoga number (1-27)
     */
    public static int calculateYoga(double sunLongitude, double moonLongitude) {
        double sum = normalizeDegrees(sunLongitude + moonLongitude);
        return (int) (sum / (DEGREES_IN_CIRCLE / TOTAL_YOGAS)) + 1;
    }

    /**
     * Calculates the karana based on the Moon's phase.
     *
     * @param moonPhase The Moon's phase angle in degrees
     * @return The karana number (1-60)
     */
    public static int calculateKarana(double moonPhase) {
        return (int) (moonPhase / (DEGREES_PER_TITHI / 2)) + 1;
    }

    /**
     * Calculates the vaara (day of the week) for a given Julian Day.
     *
     * @param jd The Julian Day
     * @return The index of the day of the week (0-6, where 0 is Sunday)
     */
    public static int calculateVaara(double jd) {
        return (int) (jd + 1.5) % 7;
    }

    /**
     * Calculates the Lagna (Ascendant) based on the local time and location.
     *
     * @param siderealTime The sidereal time in degrees
     * @param longitude    The longitude of the location
     * @return The degree of the ascendant (Lagna)
     */
    public static double calculateLagna(double siderealTime, double longitude) {
        return normalizeDegrees(siderealTime + longitude);
    }

    // Add more utility methods as needed for your specific astrological calculations
}
