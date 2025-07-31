package com.PdfCompressorApp.domain.repository;

import java.io.File;

import com.PdfCompressorApp.domain.model.UploadedFile;

public interface PdfCompressorRepository {
    File compressPdf(UploadedFile pdfFile);
}
