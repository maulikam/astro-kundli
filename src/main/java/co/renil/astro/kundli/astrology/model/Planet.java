package co.renil.astro.kundli.astrology.model;

public class Planet {
    private String name;
    private double longitude;
    private String speed;
    private String dignity;

    public Planet(String name, double longitude, String speed, String dignity) {
        this.name = name;
        this.longitude = longitude;
        this.speed = speed;
        this.dignity = dignity;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDignity() {
        return dignity;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setDignity(String dignity) {
        this.dignity = dignity;
    }
}
