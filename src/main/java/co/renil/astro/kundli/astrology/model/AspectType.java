package co.renil.astro.kundli.astrology.model;

/**
 * Enum representing different types of astrological aspects.
 */
public enum AspectType {
    CONJUNCTION("Conjunction", 0.0, 10.0),
    OPPOSITION("Opposition", 180.0, 10.0),
    TRINE("Trine", 120.0, 8.0),
    SQUARE("Square", 90.0, 8.0),
    SEXTILE("Sextile", 60.0, 6.0),
    QUINCUNX("Quincunx", 150.0, 4.0),
    SEMI_SQUARE("Semi-Square", 45.0, 2.0),
    SESQUIQUADRATE("Sesquiquadrate", 135.0, 2.0);

    private final String name;
    private final double angle;
    private final double orb;

    AspectType(String name, double angle, double orb) {
        this.name = name;
        this.angle = angle;
        this.orb = orb;
    }

    public String getName() {
        return name;
    }

    public double getAngle() {
        return angle;
    }

    public double getOrb() {
        return orb;
    }
}

