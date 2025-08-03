package com.PdfCompressorApp.infrastructure.validators;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.IOException;

@Component
public class PdfValidator {

    public void validate(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".pdf")) {
                throw new IllegalArgumentException("Nur PDF-Dateien sind erlaubt.");
            }

            String contentType = file.getContentType();
            if (contentType == null || !"application/pdf".equalsIgnoreCase(contentType)) {
                throw new IllegalArgumentException("Ungültiger Dateiinhalt (kein PDF).");
            }

            try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(file.getInputStream()))) {
            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Verarbeiten der Datei.");
        }
    }
}
