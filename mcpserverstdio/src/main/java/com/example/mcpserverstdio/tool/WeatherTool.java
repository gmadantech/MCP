package com.example.mcpserverstdio.tool;

import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.example.mcpserverstdio.service.WeatherService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WeatherTool {

	private final WeatherService weatherService;

	@Tool(name = "getWeatherByCity", description = "Get weather information by city name")
	Map<String, Object> getWeatherByCity(
			@ToolParam(description = "City name to fetch the weather information") String city) {
		return weatherService.call(Map.of("location", city)).block();
	}

}
