package com.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.model.ChatMessage;

@Controller
public class ChatController {

	//method to register who is chatting & the content of their message 
		//message = content of the message 
		//headerAccessor = hold attributes of who is chatting 
	
	@MessageMapping("/chat.register") //ensures that is a message is sent to /chat.register then register() will be called
	@SendTo("/topic/public") //specify who to send it to based on the URL
	public ChatMessage register(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) { //@Payload is used to extract the payload of the massage & have it de-serialised to the declared target type
		
		//set the headerAccessor to the username of who is chatting by getting the sender of the message 
		headerAccessor.getSessionAttributes().put("username", message.getSender()); 
		
		return message; 
	}
	
	//method to send a message 
	@MessageMapping("/chat.send") //ensures that is a message is sent to /chat.send then send() will be called
	@SendTo("/topic/public") //specify who to send it to based on the URL 
	public ChatMessage send(@Payload ChatMessage message) {  
		return message; 
	}
}
