package co.renil.astro.kundli.astrology.model;

import lombok.Data;

@Data
public class Aspect {
    private String name;       // Name of the aspect (e.g., Conjunction, Opposition)
    private double angle;      // Angle between the planets
    private double orb;        // Orb of influence for the aspect
    private String planet1;    // Name of the first planet in the aspect
    private String planet2;    // Name of the second planet in the aspect

    public Aspect(String name, double angle, double orb, String planet1, String planet2) {
        this.name = name;
        this.angle = angle;
        this.orb = orb;
        this.planet1 = planet1;
        this.planet2 = planet2;
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

    public String getPlanet1() {
        return planet1;
    }

    public String getPlanet2() {
        return planet2;
    }

    @Override
    public String toString() {
        return "Aspect{" +
                "name='" + name + '\'' +
                ", angle=" + angle +
                ", orb=" + orb +
                ", planet1='" + planet1 + '\'' +
                ", planet2='" + planet2 + '\'' +
                '}';
    }
}
