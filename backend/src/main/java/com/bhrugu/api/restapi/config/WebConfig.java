package com.bhrugu.api.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global CORS Configuration for Digital Video Store API
 * Allows frontend applications to access the backend API from different origins
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                    "http://localhost:3000",           // Local React development
                    "http://localhost:3001",           // Alternative local port
                    "http://localhost:3002",           // Alternative local port
                    "https://digital-video-store-iota.vercel.app",  // Vercel production
                    "https://*.vercel.app"             // Any Vercel deployment
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // Cache preflight response for 1 hour
    }
}
