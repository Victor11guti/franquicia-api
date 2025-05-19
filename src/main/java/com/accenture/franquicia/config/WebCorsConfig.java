package com.accenture.franquicia.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class WebCorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
    	  CorsConfiguration config = new CorsConfiguration();
          config.setAllowCredentials(true);
          config.setAllowedOrigins(List.of("https://franquicia-api-production.up.railway.app"));
          config.addAllowedHeader("*");
          config.addAllowedMethod("*");

          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**", config);
          return new CorsWebFilter(source);
    }
}
