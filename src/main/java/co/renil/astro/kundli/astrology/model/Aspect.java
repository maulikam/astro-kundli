package co.renil.astro.kundli.astrology.model;


public class Aspect {
    private String name;
    private double angle;
    private double orb;

    public Aspect(String name, double angle, double orb) {
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

