package com.example.mcpserverstdio.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class WeatherService {

    private final WebClient webClient = WebClient.builder().build();

    @Value("${weather.url-template}")
    private String urlTemplate;

    @SuppressWarnings("unchecked")
    public Mono<Map<String, Object>> call(Map<String, Object> args) {
        String city = args.get("location").toString();
        String url = String.format(urlTemplate, city);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    Map<String, Object> current = ((java.util.List<Map<String, Object>>) response.get("current_condition")).get(0);

                    return Map.of(
                            "city", city,
                            "temperature_C", current.get("temp_C"),
                            "weather_desc", ((java.util.List<Map<String, Object>>) current.get("weatherDesc")).get(0).get("value"),
                            "humidity", current.get("humidity"),
                            "feels_like_C", current.get("FeelsLikeC")
                    );
                });
    }
}
