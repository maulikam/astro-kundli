package co.renil.astro.kundli.controller;

import co.renil.astro.kundli.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;

    @PostMapping("/generate/{kundliId}")
    public ResponseEntity<byte[]> generateReport(@PathVariable UUID kundliId) {
        byte[] pdfData = reportsService.generateReport(kundliId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "kundli_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfData);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<byte[]> getReport(@PathVariable UUID reportId) {
        byte[] pdfData = reportsService.getReport(reportId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "kundli_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfData);
    }
}
