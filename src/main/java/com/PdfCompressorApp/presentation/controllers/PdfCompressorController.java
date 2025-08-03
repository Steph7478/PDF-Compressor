package com.PdfCompressorApp.presentation.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.PdfCompressorApp.application.services.PdfCompressorService;
import com.PdfCompressorApp.infrastructure.validators.PdfValidator;
import com.PdfCompressorApp.domain.entities.UploadedFile;

import java.io.File;

@RestController
@RequestMapping("/")
public class PdfCompressorController {

    private final PdfCompressorService service;
    private final PdfValidator validator;

    public PdfCompressorController(PdfCompressorService service, PdfValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @PostMapping("/compress")
    public ResponseEntity<?> compressPdf(@RequestParam("file") MultipartFile multipartFile) {
        try {
            validator.validate(multipartFile);

            File tempFile = File.createTempFile("upload_", ".pdf");
            multipartFile.transferTo(tempFile);

            UploadedFile file = new UploadedFile(multipartFile.getOriginalFilename(), tempFile);
            File compressedFile = service.compress(file);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String safeFileName = "compressed.pdf";
            headers.setContentDisposition(ContentDisposition.attachment().filename(safeFileName).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new FileSystemResource(compressedFile));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fehler bei der PDF-Kompression: " + e.getMessage());
        }
    }

}
