package com.PdfCompressorApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration config = new CorsConfiguration();

                config.setAllowedOrigins(List.of("http://localhost:8080"));
                config.setAllowedMethods(List.of("GET", "POST", "OPTIONS"));
                config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "X-Requested-With"));
                config.setAllowCredentials(true);
                config.setMaxAge(3600L);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);

                return source;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.POST, "/compress/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                                                .anyRequest().denyAll())
                                .headers(headers -> headers
                                                .defaultsDisabled()
                                                .addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options",
                                                                "nosniff"))
                                                .addHeaderWriter(new StaticHeadersWriter("X-Frame-Options", "DENY"))
                                                .addHeaderWriter(new StaticHeadersWriter("Referrer-Policy",
                                                                "no-referrer"))
                                                .addHeaderWriter(new StaticHeadersWriter("Permissions-Policy",
                                                                "geolocation=(), microphone=(), camera=()"))
                                                .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy",
                                                                "default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; object-src 'none'; base-uri 'none';"))
                                                .addHeaderWriter(new StaticHeadersWriter("Strict-Transport-Security",
                                                                "max-age=31536000; includeSubDomains"))
                                                .cacheControl(cache -> cache.disable()));

                return http.build();
        }
}
