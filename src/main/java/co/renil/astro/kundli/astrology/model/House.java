package co.renil.astro.kundli.astrology.model;

public class House {
    private int houseNumber;
    private double cuspDegree;
    private String ruler;
    private String bhavaName;

    public House(int houseNumber, double cuspDegree, String ruler, String bhavaName) {
        this.houseNumber = houseNumber;
        this.cuspDegree = cuspDegree;
        this.ruler = ruler;
        this.bhavaName = bhavaName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public double getCuspDegree() {
        return cuspDegree;
    }

    public String getRuler() {
        return ruler;
    }

    public String getBhavaName() {
        return bhavaName;
    }
}
