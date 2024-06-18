package com.mca.infrastructure.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${provider.saga.endpoint:http://server:8081}")
    private String gameSagaApiEndpoint;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(gameSagaApiEndpoint).build();
    }
}
