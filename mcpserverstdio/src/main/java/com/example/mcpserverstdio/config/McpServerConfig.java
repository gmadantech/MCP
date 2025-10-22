package com.example.mcpserverstdio.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mcpserverstdio.tool.HelpDeskTools;
import com.example.mcpserverstdio.tool.WeatherTool;

@Configuration
public class McpServerConfig {

	@Bean
	List<ToolCallback> toolCallbacks(HelpDeskTools helpDeskTools, WeatherTool weatherTool) {
		return Stream.of(helpDeskTools, weatherTool).flatMap(tool -> Arrays.stream(ToolCallbacks.from(tool)))
				.collect(Collectors.toList());
	}
}
