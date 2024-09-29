package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.entity.HoroscopeDetails;
import co.renil.astro.kundli.repository.HoroscopeDetailsRepository;
import co.renil.astro.kundli.repository.KundliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HoroscopeService {

    private final KundliRepository kundliRepository;
    private final HoroscopeDetailsRepository horoscopeDetailsRepository;

    /**
     * Generate daily horoscope predictions based on Kundli data.
     * @param kundliId The UUID of the Kundli.
     * @return Generated horoscope details.
     */
    public HoroscopeDetails generateDailyHoroscope(UUID kundliId) {
        return generateHoroscope(kundliId, "daily");
    }

    /**
     * Generate monthly horoscope predictions based on Kundli data.
     * @param kundliId The UUID of the Kundli.
     * @return Generated horoscope details.
     */
    public HoroscopeDetails generateMonthlyHoroscope(UUID kundliId) {
        return generateHoroscope(kundliId, "monthly");
    }

    /**
     * Generate yearly horoscope predictions based on Kundli data.
     * @param kundliId The UUID of the Kundli.
     * @return Generated horoscope details.
     */
    public HoroscopeDetails generateYearlyHoroscope(UUID kundliId) {
        return generateHoroscope(kundliId, "yearly");
    }

    /**
     * Get all horoscope details for a specific Kundli.
     * @param kundliId The UUID of the Kundli.
     * @return List of all horoscope details related to the Kundli.
     */
    public List<HoroscopeDetails> getHoroscopeForKundli(UUID kundliId) {
        Optional<Kundli> kundli = kundliRepository.findById(kundliId);
        if (kundli.isEmpty()) {
            throw new IllegalArgumentException("Kundli not found with id: " + kundliId);
        }

        return horoscopeDetailsRepository.findAllByKundli(kundli.get());
    }

    /**
     * Generate horoscope predictions based on Kundli data and time period.
     * @param kundliId The UUID of the Kundli.
     * @param timePeriod "daily", "monthly", or "yearly".
     * @return Generated horoscope details.
     */
    private HoroscopeDetails generateHoroscope(UUID kundliId, String timePeriod) {
        Optional<Kundli> kundliOptional = kundliRepository.findById(kundliId);

        if (kundliOptional.isEmpty()) {
            throw new IllegalArgumentException("Kundli not found with id: " + kundliId);
        }

        Kundli kundli = kundliOptional.get();

        // Placeholder logic for horoscope generation - replace with actual astrological prediction logic
        String planetaryPositions = kundli.getChartData(); // Assuming chart data has planetary positions in JSON format
        String rahuKetuPositions = kundli.getDasaPeriods(); // Placeholder for Rahu/Ketu data (you may replace with actual data)

        String predictions = String.format("General predictions for the %s period based on planetary movements for Nakshatra %s, Manglik Status %s.",
                timePeriod, kundli.getNakshatra(), kundli.getManglikStatus() ? "Yes" : "No");

        // Create HoroscopeDetails entity to store the prediction
        HoroscopeDetails horoscopeDetails = new HoroscopeDetails();
        horoscopeDetails.setKundli(kundli);
        horoscopeDetails.setHoroscopeDate(LocalDateTime.now());
        horoscopeDetails.setPlanetaryPositions(planetaryPositions);
        horoscopeDetails.setRahuKetuPositions(rahuKetuPositions);
        horoscopeDetails.setPredictions(predictions);
        horoscopeDetails.setLanguage(kundli.getLanguage());

        return horoscopeDetailsRepository.save(horoscopeDetails);
    }
}
