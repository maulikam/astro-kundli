package co.renil.astro.kundli.controller;

import org.springframework.web.bind.annotation.*;
import co.renil.astro.kundli.service.AstrologyInterpretationService;

@RestController
@RequestMapping("/api/astrology/interpretation")
public class AstrologyInterpretationController {
    private final AstrologyInterpretationService interpretationService;

    public AstrologyInterpretationController(AstrologyInterpretationService interpretationService) {
        this.interpretationService = interpretationService;
    }

    @PostMapping("/natal")
    public String getNatalInterpretation(/* parameters */) {
        return interpretationService.interpretNatalChart(/* parameters */);
    }

    @PostMapping("/transits")
    public String getTransitInterpretation(/* parameters */) {
        return interpretationService.interpretTransits(/* parameters */);
    }

    @PostMapping("/synastry")
    public String getSynastryInterpretation(/* parameters */) {
        return interpretationService.interpretSynastry(/* parameters */);
    }
}