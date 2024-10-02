package co.renil.astro.kundli.astrology.model;

import java.util.List;
import java.util.Map;

/**
 * This class represents a complete Birth Chart, containing all astrological information for an individual.
 */
public class BirthChart {

    // Fields representing the different components of the birth chart
    private House[] houses; // Array to hold 12 houses
    private List<Planet> planets; // List of planets and their positions
    private List<ZodiacSign> zodiacSigns; // List of zodiac signs corresponding to each planet
    private List<Aspect> aspects; // List of aspects formed between the planets
    private Map<String, Integer> navamsa; // Navamsa (D9) chart positions for each planet
    private String dasha; // Vimshottari Dasha currently active for the individual

    // Constructors
    public BirthChart() {
        // Default constructor
    }

    public BirthChart(House[] houses, List<Planet> planets, List<ZodiacSign> zodiacSigns,
                      List<Aspect> aspects, Map<String, Integer> navamsa, String dasha) {
        this.houses = houses;
        this.planets = planets;
        this.zodiacSigns = zodiacSigns;
        this.aspects = aspects;
        this.navamsa = navamsa;
        this.dasha = dasha;
    }

    // Getters and Setters
    public House[] getHouses() {
        return houses;
    }

    public void setHouses(House[] houses) {
        this.houses = houses;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<ZodiacSign> getZodiacSigns() {
        return zodiacSigns;
    }

    public void setZodiacSigns(List<ZodiacSign> zodiacSigns) {
        this.zodiacSigns = zodiacSigns;
    }

    public List<Aspect> getAspects() {
        return aspects;
    }

    public void setAspects(List<Aspect> aspects) {
        this.aspects = aspects;
    }

    public Map<String, Integer> getNavamsa() {
        return navamsa;
    }

    public void setNavamsa(Map<String, Integer> navamsa) {
        this.navamsa = navamsa;
    }

    public String getDasha() {
        return dasha;
    }

    public void setDasha(String dasha) {
        this.dasha = dasha;
    }

    // Utility methods

    /**
     * Returns a summary of the birth chart as a string for easy representation.
     *
     * @return A summary string containing key information of the birth chart.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Birth Chart Summary:\n");
        sb.append("Houses:\n");
        for (House house : houses) {
            sb.append(house.toString()).append("\n");
        }
        sb.append("Planets:\n");
        for (Planet planet : planets) {
            sb.append(planet.toString()).append("\n");
        }
        sb.append("Zodiac Signs:\n");
        for (ZodiacSign zodiacSign : zodiacSigns) {
            sb.append(zodiacSign.toString()).append("\n");
        }
        sb.append("Aspects:\n");
        for (Aspect aspect : aspects) {
            sb.append(aspect.toString()).append("\n");
        }
        sb.append("Navamsa Positions:\n");
        for (Map.Entry<String, Integer> entry : navamsa.entrySet()) {
            sb.append(entry.getKey()).append(": Navamsa ").append(entry.getValue()).append("\n");
        }
        sb.append("Current Vimshottari Dasha: ").append(dasha).append("\n");
        return sb.toString();
    }
}
