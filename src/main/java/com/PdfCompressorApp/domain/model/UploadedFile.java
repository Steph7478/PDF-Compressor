package com.PdfCompressorApp.domain.model;

import java.io.File;

public class UploadedFile {
    private final String filename;
    private final File file;

    public UploadedFile(String filename, File file) {
        this.filename = filename;
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public File getFile() {
        return file;
    }
}
