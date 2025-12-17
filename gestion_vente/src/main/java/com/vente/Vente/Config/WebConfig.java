package com.vente.Vente.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for the application.
 * Configures WebClient for microservices communication and CORS.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure WebClient builder for calling microservices.
     * @return WebClient.Builder
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    /**
     * Configure CORS mappings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
