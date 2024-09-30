package co.renil.astro.kundli.astrology.util;

/**
 * This class contains astronomical and astrological constants used in calculations.
 */
public final class AstronomicalConstants {

    // Prevent instantiation
    private AstronomicalConstants() {}

    // Astronomical constants
    public static final double EARTH_MEAN_RADIUS_KM = 6371.0; // Earth's mean radius in kilometers
    public static final double ASTRONOMICAL_UNIT_KM = 149597870.7; // 1 AU in kilometers
    public static final double SPEED_OF_LIGHT_KM_S = 299792.458; // Speed of light in km/s

    // Time-related constants
    public static final double SECONDS_PER_DAY = 86400.0;
    public static final double TROPICAL_YEAR = 365.242190; // Days in a tropical year
    public static final double SIDEREAL_YEAR = 365.256363004; // Days in a sidereal year

    // Astrological constants
    public static final int ZODIAC_SIGNS = 12;
    public static final double DEGREES_IN_CIRCLE = 360.0;
    public static final double DEGREES_PER_SIGN = DEGREES_IN_CIRCLE / ZODIAC_SIGNS;
    public static final int HOUSES = 12;

    // Planetary constants (mean orbital periods in days)
    public static final double MERCURY_ORBITAL_PERIOD = 87.9691;
    public static final double VENUS_ORBITAL_PERIOD = 224.701;
    public static final double MARS_ORBITAL_PERIOD = 686.98;
    public static final double JUPITER_ORBITAL_PERIOD = 4332.59;
    public static final double SATURN_ORBITAL_PERIOD = 10759.22;
    public static final double URANUS_ORBITAL_PERIOD = 30688.5;
    public static final double NEPTUNE_ORBITAL_PERIOD = 60182.0;
    public static final double PLUTO_ORBITAL_PERIOD = 90560.0;
    public static final double MOON_ORBITAL_PERIOD = 27.321582; // Days for one orbit of the Moon

    // Rahu and Ketu (lunar nodes) constants
    public static final double RAHU_KETU_MEAN_PERIOD = 6798.383; // Approximate cycle period of Rahu and Ketu

    // Sidereal and Tropical Zodiac constants
    public static final double LAHIRI_AYANAMSA = 24.0562; // Ayanamsa correction value (varies each year)

    // Orbital Eccentricity and Obliquity constants
    public static final double EARTH_ORBITAL_ECCENTRICITY = 0.0167; // Mean orbital eccentricity
    public static final double OBLIQUITY_J2000 = 23.43929111; // Earth's obliquity of ecliptic at J2000 (degrees)

    // Standard reference date constants
    public static final double J2000 = 2451545.0; // Julian date for Jan 1, 2000, at 12:00 UTC
    public static final double J1950 = 2433282.5; // Julian date for Jan 1, 1950
    public static final double J1900 = 2415020.5; // Julian date for Jan 1, 1900

    // Aspect orbs (in degrees)
    public static final double CONJUNCTION_ORB = 10.0;
    public static final double OPPOSITION_ORB = 10.0;
    public static final double TRINE_ORB = 8.0;
    public static final double SQUARE_ORB = 8.0;
    public static final double SEXTILE_ORB = 6.0;
    public static final double QUINCUNX_ORB = 4.0;
    public static final double SEMI_SQUARE_ORB = 2.0;
    public static final double SESQUIQUADRATE_ORB = 2.0;

    // Aspect angles (in degrees)
    public static final double QUINCUNX_ANGLE = 150.0;
    public static final double SEMI_SQUARE_ANGLE = 45.0;
    public static final double SESQUIQUADRATE_ANGLE = 135.0;

    // Planetary Speeds (mean orbital speeds in km/s)
    public static final double MERCURY_MEAN_SPEED = 47.87;
    public static final double VENUS_MEAN_SPEED = 35.02;
    public static final double MARS_MEAN_SPEED = 24.13;
    public static final double JUPITER_MEAN_SPEED = 13.07;
    public static final double SATURN_MEAN_SPEED = 9.69;
    public static final double URANUS_MEAN_SPEED = 6.81;
    public static final double NEPTUNE_MEAN_SPEED = 5.43;

    // Astronomical calculations constants
    public static final double DEG_TO_RAD = Math.PI / 180.0;
    public static final double RAD_TO_DEG = 180.0 / Math.PI;

    // Precession and Nutation constants
    public static final double PRECESSION_RATE = 50.2909; // Precession rate in arcseconds per year
    public static final double NUTATION_PERIOD = 6798.383; // Nutation period in days

    // House System Constants
    public enum HouseSystem {
        PLACIDUS,
        KOCH,
        WHOLE_SIGN,
        EQUAL
    }

    // Mean Anomaly, True Anomaly, and Eccentric Anomaly - placeholder (these require calculation methods)
    public static final double MEAN_ANOMALY_CONSTANT = 0.0; // Placeholder for use in future calculations

    // Epoch and Time Handling
    public static final double UNIVERSAL_TIME_CORRECTION = 0.0; // Placeholder for UT to ET conversion

    // Lunar Phases (for future calculations of new moon, full moon, etc.)
    public static final double LUNAR_PHASE_NEW = 0.0; // Placeholder for new moon phase calculation


    // Nakshatra (Lunar Mansion) constants
    public static final int TOTAL_NAKSHATRAS = 27;
    public static final double DEGREES_PER_NAKSHATRA = DEGREES_IN_CIRCLE / TOTAL_NAKSHATRAS;



    // Dasha periods (in years) for Vimshottari Dasha system
    public static final int KETU_DASHA_YEARS = 7;
    public static final int VENUS_DASHA_YEARS = 20;
    public static final int SUN_DASHA_YEARS = 6;
    public static final int MOON_DASHA_YEARS = 10;
    public static final int MARS_DASHA_YEARS = 7;
    public static final int RAHU_DASHA_YEARS = 18;
    public static final int JUPITER_DASHA_YEARS = 16;
    public static final int SATURN_DASHA_YEARS = 19;
    public static final int MERCURY_DASHA_YEARS = 17;

    // Divisional charts (Varga)
    public static final int D1_RASI_CHART = 1;
    public static final int D9_NAVAMSA_CHART = 9;
    public static final int D12_DVADASAMSA_CHART = 12;



    // Constants for Ayanamsa calculations
    public static final double AYANAMSA_2000 = 23.85;
    public static final double AYANAMSA_ANNUAL_PRECESSION = 50.2388475;  // seconds of arc per year

    // Constants for Shadbala (strength of planets) calculations
    public static final double STHANABALA_MAX = 60;
    public static final double DIGBALA_MAX = 60;
    public static final double KALABALA_MAX = 60;
    public static final double CHESTABALA_MAX = 60;
    public static final double NAISARGIKABALA_MAX = 60;
    public static final double DRIKBALA_MAX = 60;
}
