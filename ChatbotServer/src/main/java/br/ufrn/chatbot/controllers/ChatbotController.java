package br.ufrn.chatbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.chatbot.dto.ChatbotDTO;
import br.ufrn.chatbot.services.ChatbotService;

@RestController
@RequestMapping("chatbot")
public class ChatbotController {

	@Autowired
	private ChatbotService service;

	
	@PostMapping
	public ResponseEntity<String> sendQuestionToBot(@RequestBody ChatbotDTO dto){
		
		return ResponseEntity.ok().body(service.sendQuestionToBot(dto.question()));

	}
		

}
