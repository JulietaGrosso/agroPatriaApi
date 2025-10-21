package com.agropatriaapp.agropatriaapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all paths
                .allowedOrigins("http://localhost:3000", "http://localhost:5173", "https://julietagrosso.github.io", "https://agropatria.com", "https://www.agropatria.com", "https://webapp.agropatria.com") // Specify allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allowed headers
                .allowCredentials(true) // Allow sending cookies/auth headers
                .maxAge(3600); // Cache preflight response for 1 hour
    }
}