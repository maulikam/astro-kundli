package co.renil.astro.kundli.service;

import co.renil.astro.kundli.astrology.chart.BirthChartGenerator;
import co.renil.astro.kundli.astrology.model.BirthChart;
import co.renil.astro.kundli.entity.Charts;
import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.repository.ChartsRepository;
import co.renil.astro.kundli.repository.KundliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChartsService {

    private final ChartsRepository chartsRepository;
    private final KundliRepository kundliRepository;
    private final BirthChartGenerator birthChartGenerator;


    /**
     * Generate a new chart for the given Kundli.
     *
     * @param kundliId  UUID of the Kundli for which the chart is being generated.
     * @param chartType Type of the chart (e.g., North Indian, South Indian, KP, Western).
     * @return The generated Charts entity.
     */
    @Transactional
    public Charts generateChart(UUID kundliId, String chartType) {
        Optional<Kundli> optionalKundli = kundliRepository.findById(kundliId);

        if (optionalKundli.isEmpty()) {
            throw new IllegalArgumentException("Kundli not found with id " + kundliId);
        }

        Kundli kundli = optionalKundli.get();

        // Generate birth chart data using BirthChartGenerator
        BirthChart birthChart = generateBirthChart(kundli);

        // Convert BirthChart object to a string representation (e.g., JSON) to save as chart data
        String chartData = convertBirthChartToString(birthChart);

        // Create and save the Charts entity
        Charts chart = new Charts();
        chart.setKundli(kundli);
        chart.setChartType(chartType);
        chart.setChartData(chartData);

        return chartsRepository.save(chart);
    }

    /**
     * Generate a birth chart for a specific Kundli.
     *
     * @return The generated BirthChart as a Charts entity.
     */
    @PostMapping("/generate-birth-chart")
    public ResponseEntity<Charts> generateBirthChart(@RequestParam UUID kundliId) {
        try {
            Charts birthChart = this.generateChart(kundliId, "BirthChart");
            return ResponseEntity.ok(birthChart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieve charts by Kundli ID.
     *
     * @param kundliId The UUID of the Kundli for which charts are being retrieved.
     * @return List of charts associated with the Kundli.
     */
    public List<Charts> getChartsByKundliId(UUID kundliId) {
        return chartsRepository.findByKundliId(kundliId);
    }

    /**
     * Retrieve a specific chart by its ID.
     *
     * @param chartId The UUID of the chart.
     * @return The Charts entity, if found.
     */
    public Optional<Charts> getChartById(UUID chartId) {
        return chartsRepository.findById(chartId);
    }

    /**
     * Generate a birth chart for a given Kundli using BirthChartGenerator.
     *
     * @param kundli The Kundli entity containing birth details.
     * @return A BirthChart object with generated data.
     */
    private BirthChart generateBirthChart(Kundli kundli) {
        // Convert birthDate and birthTime into LocalDateTime
        LocalDateTime birthDateTime = LocalDateTime.of(
                kundli.getBirthDate().toLocalDate(),
                kundli.getBirthTime().toLocalTime()
        );
        double longitude = kundli.getLongitude();
        double latitude = kundli.getLatitude();

        return birthChartGenerator.generateBirthChart(birthDateTime, longitude, latitude);
    }

    /**
     * Converts a BirthChart object to a string representation (e.g., JSON).
     *
     * @param birthChart The BirthChart object to convert.
     * @return The string representation of the birth chart.
     */
    private String convertBirthChartToString(BirthChart birthChart) {
        // Placeholder logic to convert BirthChart to String (e.g., JSON serialization)
        // You could use a library like Jackson or Gson here to convert to JSON
        return birthChart.toString();
    }
}
