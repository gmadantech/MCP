package com.example.mcpserverstdio.tool;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcpserverstdio.service.WeatherService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

	private final WeatherService weatherService;

	@GetMapping("/weather/{location}")
	public Mono<ResponseEntity<Map<String, Object>>> getWeather(@PathVariable("location") String location) {
		Map<String, Object> args = Map.of("location", location);
		return weatherService.call(args).map(body -> ResponseEntity.ok(body))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
