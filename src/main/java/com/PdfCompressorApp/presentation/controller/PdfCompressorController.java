package com.PdfCompressorApp.presentation.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.PdfCompressorApp.application.service.PdfCompressorService;
import com.PdfCompressorApp.domain.model.UploadedFile;

import java.io.File;

@RestController
@RequestMapping("/")
public class PdfCompressorController {

    private final PdfCompressorService service;

    public PdfCompressorController(PdfCompressorService service) {
        this.service = service;
    }

    @PostMapping("/compress")
    public ResponseEntity<?> compressPdf(@RequestParam("file") MultipartFile multipartFile) {
        File tempFile = null;
        File compressedFile = null;

        try {
            tempFile = File.createTempFile("upload_", ".pdf");
            multipartFile.transferTo(tempFile);

            UploadedFile file = new UploadedFile(multipartFile.getOriginalFilename(), tempFile);
            compressedFile = service.compress(file);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            String safeFileName = "compressed.pdf";

            headers.setContentDisposition(ContentDisposition.attachment().filename(safeFileName).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new FileSystemResource(compressedFile));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fehler bei der PDF-Kompression: " + e.getMessage());
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
