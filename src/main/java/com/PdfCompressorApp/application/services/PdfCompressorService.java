package com.PdfCompressorApp.application.services;

import org.springframework.stereotype.Service;

import com.PdfCompressorApp.domain.entities.UploadedFile;
import com.PdfCompressorApp.domain.repositories.PdfCompressorRepository;

import java.io.File;

@Service
public class PdfCompressorService {

    private final PdfCompressorRepository pdfCompressorRepository;

    public PdfCompressorService(PdfCompressorRepository pdfCompressorRepository) {
        this.pdfCompressorRepository = pdfCompressorRepository;
    }

    public File compress(UploadedFile file) {
        return pdfCompressorRepository.compressPdf(file);
    }
}
