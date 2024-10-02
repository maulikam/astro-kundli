package co.renil.astro.kundli.astrology.model;

public class ZodiacSign {
    private String name;
    private double startDegree;
    private double endDegree;
    private String rulingPlanet;

    public ZodiacSign(String name, double startDegree, double endDegree, String rulingPlanet) {
        this.name = name;
        this.startDegree = startDegree;
        this.endDegree = endDegree;
        this.rulingPlanet = rulingPlanet;
    }

    public String getName() {
        return name;
    }

    public double getStartDegree() {
        return startDegree;
    }

    public double getEndDegree() {
        return endDegree;
    }

    public String getRulingPlanet() {
        return rulingPlanet;
    }
}
