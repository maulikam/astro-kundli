package co.renil.astro.kundli.astrology.util;

/**
 * This class contains refined astronomical and astrological constants used in calculations.
 */
public final class AstronomicalConstants {

    // Prevent instantiation
    private AstronomicalConstants() {}

    // Fundamental constants
    public static final double SPEED_OF_LIGHT_KM_S = 299792.458; // Speed of light in km/s
    public static final double DEG_TO_RAD = Math.PI / 180.0;
    public static final double RAD_TO_DEG = 180.0 / Math.PI;

    // Astronomical constants
    public static final double EARTH_MEAN_RADIUS_KM = 6371.0; // Earth's mean radius in kilometers
    public static final double ASTRONOMICAL_UNIT_KM = 149597870.7; // 1 AU in kilometers
    public static final double EARTH_ORBITAL_ECCENTRICITY = 0.0167; // Mean orbital eccentricity
    public static final double OBLIQUITY_J2000 = 23.43929111; // Earth's obliquity of ecliptic at J2000 (degrees)

    // Time-related constants
    public static final double SECONDS_PER_DAY = 86400.0;
    public static final double TROPICAL_YEAR = 365.242190; // Days in a tropical year
    public static final double SIDEREAL_YEAR = 365.256363004; // Days in a sidereal year
    public static final double JULIAN_CENTURY = 36525.0; // Days in a Julian century
    public static final double DAYS_PER_YEAR = 365.2425; // Average days in a year (considering leap years)

    // Standard reference date constants
    public static final double J2000 = 2451545.0; // Julian date for Jan 1, 2000, at 12:00 UTC
    public static final double J1950 = 2433282.5; // Julian date for Jan 1, 1950
    public static final double J1900 = 2415020.5; // Julian date for Jan 1, 1900

    // Astrological constants
    public static final int ZODIAC_SIGNS = 12;
    public static final double DEGREES_IN_CIRCLE = 360.0;
    public static final double DEGREES_PER_SIGN = DEGREES_IN_CIRCLE / ZODIAC_SIGNS;
    public static final int HOUSES = 12;

    // Planetary orbital periods (in Earth days)
    public static final double MERCURY_ORBITAL_PERIOD = 87.9691;
    public static final double VENUS_ORBITAL_PERIOD = 224.701;
    public static final double MARS_ORBITAL_PERIOD = 686.98;
    public static final double JUPITER_ORBITAL_PERIOD = 4332.59;
    public static final double SATURN_ORBITAL_PERIOD = 10759.22;
    public static final double URANUS_ORBITAL_PERIOD = 30688.5;
    public static final double NEPTUNE_ORBITAL_PERIOD = 60182.0;
    public static final double PLUTO_ORBITAL_PERIOD = 90560.0;
    public static final double MOON_ORBITAL_PERIOD = 27.321582;

    // Lunar nodes constants
    public static final double RAHU_KETU_MEAN_PERIOD = 6793.5; // Approximate cycle period of Rahu and Ketu
    public static final double RAHU_KETU_SPEED_DEG_PER_DAY = -0.05295; // Retrograde movement in degrees per day

    // Zodiac and precession constants
    public static final double LAHIRI_AYANAMSA = 24.0562; // Ayanamsa correction value (varies each year)
    public static final double PRECESSION_RATE = 50.290966; // Precession rate in arcseconds per year
    public static final double PRECESSION_CORRECTION_PER_YEAR = 0.01397; // Degrees per year

    // Aspect constants
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

    // Planetary mean orbital speeds (km/s)
    public static final double MERCURY_MEAN_SPEED = 47.87;
    public static final double VENUS_MEAN_SPEED = 35.02;
    public static final double MARS_MEAN_SPEED = 24.13;
    public static final double JUPITER_MEAN_SPEED = 13.07;
    public static final double SATURN_MEAN_SPEED = 9.69;
    public static final double URANUS_MEAN_SPEED = 6.81;
    public static final double NEPTUNE_MEAN_SPEED = 5.43;

    // Nakshatra constants
    public static final int TOTAL_NAKSHATRAS = 27;
    public static final double DEGREES_PER_NAKSHATRA = DEGREES_IN_CIRCLE / TOTAL_NAKSHATRAS;

    // Vimshottari Dasha periods (in years)
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

    // Lunar and eclipse constants
    public static final double LUNAR_SYNODIC_PERIOD = 29.53058867; // Days
    public static final double ECLIPSE_THRESHOLD_DEGREES = 18.0; // General threshold for eclipses near lunar nodes
    public static final double SOLAR_ECLIPSE_THRESHOLD_DEGREES = 15.0; // Maximum distance from lunar nodes for a solar eclipse
    public static final double LUNAR_ECLIPSE_THRESHOLD_DEGREES = 12.0; // Maximum distance from lunar nodes for a lunar eclipse
    public static final double NEW_MOON_PHASE_ANGLE = 0.0; // Degrees
    public static final double FULL_MOON_PHASE_ANGLE = 180.0; // Degrees

    // Sun's orbital elements
    public static final double SUN_MEAN_LONGITUDE = 280.46646; // Degrees, approximate mean longitude at J2000
    public static final double SUN_LATITUDE = 0.0; // The Sun's apparent latitude is considered 0 degrees
    public static final double SUN_ORBITAL_RADIUS = ASTRONOMICAL_UNIT_KM;
    public static final double SUN_ORBITAL_INCLINATION = 0.0; // Degrees

    // Planetary orbital inclinations (degrees)
    public static final double EARTH_ORBITAL_INCLINATION = 0.0; // For reference
    public static final double MERCURY_ORBITAL_INCLINATION = 7.005;
    public static final double VENUS_ORBITAL_INCLINATION = 3.39458;

    // Panchanga elements
    public static final double DEGREES_PER_TITHI = 12.0; // 360 degrees divided by 30 Tithis
    public static final int TOTAL_KARANAS = 11;
    public static final int TOTAL_YOGAS = 27;

    // Time divisions
    public static final int DAY_TIME_SEGMENTS = 8; // Daylight divided into 8 segments for Rahu Kaal
    public static final int TOTAL_GHATIKAS = 15; // Each day and night divided into 15 parts for muhurta
    public static final double MINUTES_PER_GHATIKA = 24; // 1 Ghatika is approximately 24 minutes

    // Miscellaneous constants
    public static final double SOLAR_DECLINATION_CONSTANT = 23.44; // Degrees, Earth's axial tilt
    public static final double SIDEREAL_TIME_CONSTANT = 1.00273790935; // Conversion factor from solar to sidereal time

    // Arrays
    public static final String[] DAYS_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final String[] RULING_PLANETS_OF_WEEKDAYS = {"Sun", "Moon", "Mars", "Mercury", "Jupiter", "Venus", "Saturn"};

    // Nakshatras (Lunar Mansions)
    public static final String[] NAKSHATRA_NAMES = {
            "Ashwini", "Bharani", "Krittika", "Rohini", "Mrigashira", "Ardra", "Punarvasu", "Pushya", "Ashlesha",
            "Magha", "Purva Phalguni", "Uttara Phalguni", "Hasta", "Chitra", "Swati", "Vishakha", "Anuradha", "Jyeshtha",
            "Mula", "Purva Ashadha", "Uttara Ashadha", "Shravana", "Dhanishta", "Shatabhisha", "Purva Bhadrapada",
            "Uttara Bhadrapada", "Revati"
    };
    // Nakshatra Lords (Single cycle representation)
    public static final String[] NAKSHATRA_LORDS = {
            "Ketu", "Venus", "Sun", "Moon", "Mars", "Rahu", "Jupiter", "Saturn", "Mercury",
            "Ketu", "Venus", "Sun", "Moon", "Mars", "Rahu", "Jupiter", "Saturn", "Mercury",
            "Ketu", "Venus", "Sun", "Moon", "Mars", "Rahu", "Jupiter", "Saturn", "Mercury"
    };


    // Zodiac Signs (Rashi)
    public static final String[] ZODIAC_SIGN_NAMES = {
            "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
            "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    };
    public static final String[] ZODIAC_SIGN_LORDS = {
            "Mars", "Venus", "Mercury", "Moon", "Sun", "Mercury",
            "Venus", "Mars", "Jupiter", "Saturn", "Saturn", "Jupiter"
    };

    // Planetary States
    public static final double EXALTATION_DEGREE = 0.0; // Degrees of exaltation for planets (specific to each planet)
    public static final double DEBILITATION_DEGREE = 180.0; // Degrees of debilitation for planets (specific to each planet)

    // Yogas (Planetary Combinations)
    public static final int TOTAL_MAJOR_YOGAS = 16; // Number of major yogas in Vedic astrology

    // Dasha Systems
    public static final int VIMSHOTTARI_DASHA_YEARS = 120; // Total years in Vimshottari Dasha cycle
    public static final String[] DASHA_PLANETS = {
            "Ketu", "Venus", "Sun", "Moon", "Mars", "Rahu", "Jupiter", "Saturn", "Mercury"
    };

    // Divisional Charts (Varga)
    public static final int D2_HORA_CHART = 2;
    public static final int D3_DREKKANA_CHART = 3;
    public static final int D4_CHATURTHAMSA_CHART = 4;
    public static final int D7_SAPTAMSA_CHART = 7;
    public static final int D10_DASAMSA_CHART = 10;
    public static final int D16_SHODASAMSA_CHART = 16;
    public static final int D20_VIMSHAMSA_CHART = 20;
    public static final int D24_CHATURVIMSHAMSA_CHART = 24;
    public static final int D27_NAKSHATRAMSA_CHART = 27;
    public static final int D30_TRIMSHAMSA_CHART = 30;
    public static final int D40_KHAVEDAMSA_CHART = 40;
    public static final int D45_AKSHAVEDAMSA_CHART = 45;
    public static final int D60_SHASHTYAMSA_CHART = 60;

    // Ayanamsa (Precession of Equinoxes)
    public static final double AYANAMSA_2000 = 23.85; // Ayanamsa value for year 2000
    public static final double AYANAMSA_ANNUAL_PRECESSION = 50.2388475; // Annual precession in arc seconds

    // Shadbala (Strength of Planets)
    public static final double STHANABALA_MAX = 60;
    public static final double DIGBALA_MAX = 60;
    public static final double KALABALA_MAX = 60;
    public static final double CHESTABALA_MAX = 60;
    public static final double NAISARGIKABALA_MAX = 60;
    public static final double DRIKBALA_MAX = 60;

    // Bhava (House) Related
    public static final int TOTAL_BHAVAS = 12;
    public static final String[] BHAVA_NAMES = {
            "Lagna", "Dhana", "Sahaja", "Sukha", "Putra", "Ari", "Yuvati",
            "Mrityu", "Dharma", "Karma", "Labha", "Vyaya"
    };

    // Graha Drishti (Planetary Aspects)
    public static final int[] JUPITER_ASPECTS = {5, 7, 9}; // Houses aspected by Jupiter
    public static final int[] MARS_ASPECTS = {4, 7, 8}; // Houses aspected by Mars
    public static final int[] SATURN_ASPECTS = {3, 7, 10}; // Houses aspected by Saturn

    // Muhurta (Electional Astrology)
    public static final int TOTAL_MUHURTAS = 30; // Number of muhurtas in a day

    // Lagna (Ascendant) calculation
    public static final double LAGNA_PROGRESSION_PER_HOUR = 15.0; // Degrees the Lagna moves per hour

    // Kala (Time) divisions
    public static final int TOTAL_KALA_HORAS = 24; // Number of Horas in a day
    public static final int TOTAL_KALA_GHATIKAS = 60; // Number of Ghatikas in a day
    public static final int TOTAL_KALA_VIGHATIKAS = 60; // Number of Vighatikas in a Ghatika

    // Additional arrays for Vedic astrology
    public static final String[] NAVAGRAHA = {"Sun", "Moon", "Mars", "Mercury", "Jupiter", "Venus", "Saturn", "Rahu", "Ketu"};
    public static final String[] ELEMENTS = {"Fire", "Earth", "Air", "Water"};
    public static final String[] GUNAS = {"Sattva", "Rajas", "Tamas"};

    // House System Constants
    public enum HouseSystem {
        PLACIDUS,
        KOCH,
        WHOLE_SIGN,
        EQUAL
    }

    public enum PlanetaryDignity {
        DOMICILE,
        EXALTATION,
        DETRIMENT,
        FALL
    }

    public enum PlanetaryStation {
        DIRECT,
        RETROGRADE,
        STATIONARY
    }

    public enum Yoga {
        // Wealth and Power Yogas
        RAJA_YOGA,
        DHANA_YOGA,
        GAJAKESARI_YOGA,
        ADHI_YOGA,
        VASUMATI_YOGA,
        LAKSHMI_YOGA,
        MAHAPURUSHA_YOGA,

        // Spiritual and Enlightenment Yogas
        AMALA_YOGA,
        PARVATA_YOGA,
        KAHALA_YOGA,
        VESI_YOGA,
        OBHAYACHARI_YOGA,

        // Career and Success Yogas
        BHADRA_YOGA,
        HAMSA_YOGA,
        RUCHAKA_YOGA,
        MALAVYA_YOGA,
        SASA_YOGA,

        // Relationship Yogas
        KAMA_YOGA,
        MAHABHAGYA_YOGA,

        // Challenging Yogas
        KEMDRUM_YOGA,
        SHAKAT_YOGA,
        VIPAREETA_RAJA_YOGA,
        DARIDRA_YOGA,

        // Rare and Powerful Yogas
        PANCHA_MAHAPURUSHA_YOGA,
        NEECHA_BHANGA_RAJA_YOGA,

        // Intellectual and Communication Yogas
        SARASWATI_YOGA,
        BUDHA_ADITYA_YOGA,

        // Yogas related to Parents
        MATRU_MAHA_YOGA,
        PITRU_MAHA_YOGA,

        // Yogas related to Siblings
        BHRATRU_YOGA,

        // Yogas related to Children
        SANTANA_YOGA,

        // Yogas related to Longevity
        AYUR_YOGA,

        // Yogas related to Fame
        YASHO_YOGA,

        // Miscellaneous Yogas
        SUNAPHA_YOGA,
        ANAPHA_YOGA,
        DURADHARA_YOGA,
        KEMADRUMA_YOGA,
        CHANDRA_MANGALA_YOGA,
        PARIJATA_YOGA,
        UTTAMA_YOGA,
        VADHA_YOGA,
        VARYAAN_YOGA,
        VAJRA_YOGA,
        YAVA_YOGA,
        MUSALA_YOGA,
        NAGA_YOGA,
        ASURA_YOGA
    }
}