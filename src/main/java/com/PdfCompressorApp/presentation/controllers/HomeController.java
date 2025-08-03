package com.PdfCompressorApp.presentation.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String nonce = (String) request.getAttribute("cspNonce");
        model.addAttribute("cspNonce", nonce);
        return "index";
    }
}
