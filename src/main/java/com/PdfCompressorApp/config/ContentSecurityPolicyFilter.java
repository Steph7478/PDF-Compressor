package com.PdfCompressorApp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ContentSecurityPolicyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        byte[] nonceBytes = new byte[16];
        new SecureRandom().nextBytes(nonceBytes);
        String nonce = Base64.getEncoder().encodeToString(nonceBytes);

        request.setAttribute("cspNonce", nonce);

        String csp = "default-src 'self'; " +
                "script-src 'self' 'nonce-" + nonce + "'; " +
                "object-src 'none'; " +
                "base-uri 'self'; " +
                "img-src 'self' data:; " +
                "connect-src 'self'; " +
                "worker-src 'self' blob:; " +
                "child-src 'self' blob:; " +
                "frame-ancestors 'none'; " +
                "form-action 'self'; " +
                "block-all-mixed-content; " +
                "upgrade-insecure-requests;";

        response.setHeader("Content-Security-Policy", csp);

        filterChain.doFilter(request, response);
    }
}
