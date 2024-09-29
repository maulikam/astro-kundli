package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.entity.Reports;
import co.renil.astro.kundli.repository.ReportsRepository;
import co.renil.astro.kundli.repository.KundliRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final ReportsRepository reportsRepository;
    private final KundliRepository kundliRepository;

    /**
     * Generate a PDF report for the given Kundli.
     *
     * @param kundliId UUID of the Kundli.
     * @return The generated report as a byte array.
     */
    public byte[] generateReport(UUID kundliId) {
        Optional<Kundli> kundliOptional = kundliRepository.findById(kundliId);

        if (kundliOptional.isEmpty()) {
            throw new IllegalArgumentException("Kundli not found with id: " + kundliId);
        }

        Kundli kundli = kundliOptional.get();

        // Create PDF using iText
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Add title and Kundli details to the PDF
        document.add(new Paragraph("Kundli Report"));
        document.add(new Paragraph("Name: " + kundli.getPerson().getFullName()));
        document.add(new Paragraph("Birth Date: " + kundli.getBirthDate()));
        document.add(new Paragraph("Birth Time: " + kundli.getBirthTime()));
        document.add(new Paragraph("Nakshatra: " + kundli.getNakshatra()));
        document.add(new Paragraph("Manglik Status: " + (kundli.getManglikStatus() ? "Yes" : "No")));

        // Add more data like planetary positions, predictions, etc.
        document.add(new Paragraph("Bhava Predictions: " + kundli.getBhavaPredictions()));
        document.close();

        // Save the generated report
        byte[] pdfData = byteArrayOutputStream.toByteArray();
        saveReport(kundli, pdfData);

        return pdfData;
    }

    /**
     * Save the generated report to the database.
     *
     * @param kundli The Kundli for which the report is generated.
     * @param pdfData The PDF content in byte array.
     */
    private void saveReport(Kundli kundli, byte[] pdfData) {
        Reports report = new Reports();
        report.setKundli(kundli);
        report.setReportData(pdfData);
        report.setPartyName(kundli.getPerson().getFullName());  // Customize party name if needed
        report.setPartyAddress("Address Placeholder");  // You can pass or customize address if needed
        report.setPrintStyle("Default Style");  // Customize if needed
        report.setLanguage(kundli.getLanguage());
        report.setCreatedAt(LocalDateTime.now());

        reportsRepository.save(report);
    }

    /**
     * Retrieve the report by its ID.
     *
     * @param reportId The UUID of the report.
     * @return The report as a byte array.
     */
    public byte[] getReport(UUID reportId) {
        Optional<Reports> reportOptional = reportsRepository.findById(reportId);
        if (reportOptional.isEmpty()) {
            throw new IllegalArgumentException("Report not found with id: " + reportId);
        }
        return reportOptional.get().getReportData();
    }
}
