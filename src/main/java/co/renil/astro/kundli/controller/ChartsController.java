package co.renil.astro.kundli.controller;

import co.renil.astro.kundli.entity.Charts;
import co.renil.astro.kundli.service.ChartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/charts")
@RequiredArgsConstructor
public class ChartsController {

    private final ChartsService chartsService;

    /**
     * Generate a new chart for a given Kundli.
     *
     * @param kundliId  UUID of the Kundli.
     * @param chartType Type of the chart (e.g., North Indian, South Indian, KP, Western).
     * @return The generated Charts entity.
     */
    @PostMapping("/generate")
    public ResponseEntity<Charts> generateChart(@RequestParam UUID kundliId, @RequestParam String chartType) {
        Charts chart = chartsService.generateChart(kundliId, chartType);
        return ResponseEntity.ok(chart);
    }

    /**
     * Retrieve a specific chart by its ID.
     *
     * @param chartId The UUID of the chart.
     * @return The Charts entity, if found.
     */
    @GetMapping("/{chartId}")
    public ResponseEntity<Charts> getChartById(@PathVariable UUID chartId) {
        Optional<Charts> chart = chartsService.getChartById(chartId);
        return chart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieve all charts associated with a specific Kundli.
     *
     * @param kundliId The UUID of the Kundli.
     * @return List of Charts entities associated with the Kundli.
     */
    @GetMapping("/kundli/{kundliId}")
    public ResponseEntity<List<Charts>> getChartsByKundliId(@PathVariable UUID kundliId) {
        List<Charts> charts = chartsService.getChartsByKundliId(kundliId);
        return ResponseEntity.ok(charts);
    }
}
