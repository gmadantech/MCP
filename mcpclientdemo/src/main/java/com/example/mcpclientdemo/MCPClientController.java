package com.example.mcpclientdemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MCPClientController {

	private final ChatClient chatClient;

	public MCPClientController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
		this.chatClient = chatClientBuilder
				.defaultToolCallbacks(toolCallbackProvider) // attach tool callbacks
				.defaultAdvisors(new SimpleLoggerAdvisor()) // attach advisors (logging, etc.)
				.build(); // create the final ChatClient instance
	}

	@GetMapping("/chat")
	public String chat(@RequestHeader(value = "username", required = false) String username,
			@RequestParam("message") String message) {
		return chatClient.prompt().user(message + " My username is " + username).call().content();
	}

}