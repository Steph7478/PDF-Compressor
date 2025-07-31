package com.PdfCompressorApp.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.PdfCompressorApp.domain.model.UploadedFile;
import com.PdfCompressorApp.domain.repository.PdfCompressorRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
public class PdfCompressorRepositoryImpl implements PdfCompressorRepository {

    @Override
    public File compressPdf(UploadedFile uploadedFile) {
        File input = uploadedFile.getFile();
        File output;

        try {
            output = File.createTempFile("compressed_", ".pdf");

            WriterProperties writerProperties = new WriterProperties()
                    .setCompressionLevel(9);

            try (
                    FileInputStream fis = new FileInputStream(input);
                    FileOutputStream fos = new FileOutputStream(output);
                    PdfDocument pdfDoc = new PdfDocument(new PdfReader(fis), new PdfWriter(fos, writerProperties))) {

            }

        } catch (IOException e) {
            throw new RuntimeException("Fehler bei der PDF-Kompression mit iText", e);
        }

        return output;
    }

}
