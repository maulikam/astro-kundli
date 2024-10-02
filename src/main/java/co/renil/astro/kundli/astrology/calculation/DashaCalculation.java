package co.renil.astro.kundli.astrology.calculation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static co.renil.astro.kundli.astrology.util.AstronomicalConstants.*;

public class DashaCalculation {

    /**
     * Calculates the current Vimshottari Dasha for a given birth time and Moon's longitude.
     *
     * @param moonLongitude The longitude of the Moon at the time of birth
     * @param birthTime     The birth time of the individual
     * @param currentTime   The current time
     * @return The current Vimshottari Dasha planet
     */
    public static String calculateVimshottariDasha(double moonLongitude, LocalDateTime birthTime, LocalDateTime currentTime) {
        // Determine the Nakshatra at the time of birth
        int nakshatraIndex = (int) (moonLongitude / DEGREES_PER_NAKSHATRA);
        String startingDashaPlanet = NAKSHATRA_LORDS[nakshatraIndex];

        // Calculate remaining period of the starting Dasha
        double degreesInNakshatra = moonLongitude % DEGREES_PER_NAKSHATRA;
        double remainingNakshatraFraction = 1.0 - (degreesInNakshatra / DEGREES_PER_NAKSHATRA);
        double startingDashaYears = getDashaPeriod(startingDashaPlanet);
        double startingDashaDuration = startingDashaYears * remainingNakshatraFraction;

        // Calculate total elapsed years since birth
        long totalElapsedDays = ChronoUnit.DAYS.between(birthTime, currentTime);
        double totalElapsedYears = totalElapsedDays / DAYS_PER_YEAR;

        // Determine the current Vimshottari Dasha by cycling through the Dasha planets
        String currentDashaPlanet = startingDashaPlanet;
        double elapsedYears = startingDashaDuration;

        while (elapsedYears < totalElapsedYears) {
            currentDashaPlanet = getNextDashaPlanet(currentDashaPlanet);
            elapsedYears += getDashaPeriod(currentDashaPlanet);
        }

        return currentDashaPlanet;
    }

    /**
     * Gets the Dasha period for a given planet.
     *
     * @param planet The planet whose Dasha period is to be determined
     * @return The Dasha period in years
     */
    private static double getDashaPeriod(String planet) {
        switch (planet.toLowerCase()) {
            case "ketu":
                return KETU_DASHA_YEARS;
            case "venus":
                return VENUS_DASHA_YEARS;
            case "sun":
                return SUN_DASHA_YEARS;
            case "moon":
                return MOON_DASHA_YEARS;
            case "mars":
                return MARS_DASHA_YEARS;
            case "rahu":
                return RAHU_DASHA_YEARS;
            case "jupiter":
                return JUPITER_DASHA_YEARS;
            case "saturn":
                return SATURN_DASHA_YEARS;
            case "mercury":
                return MERCURY_DASHA_YEARS;
            default:
                throw new IllegalArgumentException("Unknown planet: " + planet);
        }
    }

    /**
     * Gets the next Dasha planet in the Vimshottari Dasha cycle.
     *
     * @param currentPlanet The current Dasha planet
     * @return The next Dasha planet
     */
    private static String getNextDashaPlanet(String currentPlanet) {
        int currentIndex = -1;
        for (int i = 0; i < DASHA_PLANETS.length; i++) {
            if (DASHA_PLANETS[i].equalsIgnoreCase(currentPlanet)) {
                currentIndex = i;
                break;
            }
        }
        if (currentIndex == -1) {
            throw new IllegalArgumentException("Unknown planet: " + currentPlanet);
        }
        return DASHA_PLANETS[(currentIndex + 1) % DASHA_PLANETS.length];
    }
}
