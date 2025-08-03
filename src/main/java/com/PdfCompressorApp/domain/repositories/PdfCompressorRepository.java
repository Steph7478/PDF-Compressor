package com.PdfCompressorApp.domain.repositories;

import java.io.File;

import com.PdfCompressorApp.domain.entities.UploadedFile;

public interface PdfCompressorRepository {
    File compressPdf(UploadedFile pdfFile);
}
