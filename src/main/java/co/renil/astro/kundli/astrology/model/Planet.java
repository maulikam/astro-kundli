package co.renil.astro.kundli.astrology.model;

import lombok.Data;

@Data
public class Planet {

    private String name; // Name of the planet
    private double longitude; // Position of the planet in degrees
    private double speed; // Speed of the planet (used to determine retrograde motion)
    private String dignity; // Dignity (e.g., Exalted, Debilitated, Normal)
    private boolean retrograde; // Is the planet in retrograde?
    private double shadbala; // Shadbala (strength of the planet)
    private boolean combust;

    // Custom constructor to initialize name and longitude
    public Planet(String name, double longitude) {
        this.name = name;
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", dignity='" + dignity + '\'' +
                ", retrograde=" + retrograde +
                ", shadbala=" + shadbala +
                ", combust=" + combust +
                '}';
    }

}
