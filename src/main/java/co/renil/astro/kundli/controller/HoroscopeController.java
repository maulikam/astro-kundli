package co.renil.astro.kundli.controller;

import co.renil.astro.kundli.entity.HoroscopeDetails;
import co.renil.astro.kundli.service.HoroscopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/horoscope")
@RequiredArgsConstructor
public class HoroscopeController {

    private final HoroscopeService horoscopeService;

    @PostMapping("/daily/{kundliId}")
    public ResponseEntity<HoroscopeDetails> generateDailyHoroscope(@PathVariable UUID kundliId) {
        HoroscopeDetails horoscope = horoscopeService.generateDailyHoroscope(kundliId);
        return ResponseEntity.ok(horoscope);
    }

    @PostMapping("/monthly/{kundliId}")
    public ResponseEntity<HoroscopeDetails> generateMonthlyHoroscope(@PathVariable UUID kundliId) {
        HoroscopeDetails horoscope = horoscopeService.generateMonthlyHoroscope(kundliId);
        return ResponseEntity.ok(horoscope);
    }

    @PostMapping("/yearly/{kundliId}")
    public ResponseEntity<HoroscopeDetails> generateYearlyHoroscope(@PathVariable UUID kundliId) {
        HoroscopeDetails horoscope = horoscopeService.generateYearlyHoroscope(kundliId);
        return ResponseEntity.ok(horoscope);
    }

    @GetMapping("/kundli/{kundliId}")
    public ResponseEntity<List<HoroscopeDetails>> getHoroscopeForKundli(@PathVariable UUID kundliId) {
        List<HoroscopeDetails> horoscopes = horoscopeService.getHoroscopeForKundli(kundliId);
        return ResponseEntity.ok(horoscopes);
    }
}
