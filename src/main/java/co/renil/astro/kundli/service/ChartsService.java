package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Charts;
import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.repository.ChartsRepository;
import co.renil.astro.kundli.repository.KundliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChartsService {

    private final ChartsRepository chartsRepository;
    private final KundliRepository kundliRepository;

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

        // Placeholder logic for generating chart data based on chart type
        String chartData = generateChartData(chartType, kundli);

        // Create and save the Charts entity
        Charts chart = new Charts();
        chart.setKundli(kundli);
        chart.setChartType(chartType);
        chart.setChartData(chartData);

        return chartsRepository.save(chart);
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
     * Generate chart data for a given Kundli.
     * Placeholder logic: You need to replace this with actual astrology logic.
     *
     * @param chartType Type of the chart.
     * @param kundli    The Kundli entity containing the birth details.
     * @return Chart data in String or JSON format.
     */
    private String generateChartData(String chartType, Kundli kundli) {
        // Placeholder logic for generating astrological chart data
        // Actual logic can involve complex astrology calculations
        return String.format("Generated %s chart for Kundli with ID %s", chartType, kundli.getId());
    }
}
