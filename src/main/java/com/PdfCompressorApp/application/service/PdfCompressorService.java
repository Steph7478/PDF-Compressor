package com.PdfCompressorApp.application.service;

import org.springframework.stereotype.Service;

import com.PdfCompressorApp.domain.model.UploadedFile;
import com.PdfCompressorApp.domain.repository.PdfCompressorRepository;

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
