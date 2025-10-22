package com.example.openrouterdemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ChatController {

	private final ChatModel chatModel;

	public ChatController(ChatModel chatModel) {
		this.chatModel = chatModel;
		log.info("ChatController (instance) initialized with Spring AI ChatModel.");
	}

	@GetMapping("/chat")
	public ResponseEntity<String> chatWithOpenRouter(@RequestParam("message") String message) {
		try {
			String response = this.chatModel.call(message);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error processing chat request via Spring AI: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, an error occurred while communicating with the AI service.");
		}
	}

}
