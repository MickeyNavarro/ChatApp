package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration 
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { 
		//allows for connection fallback if connection is down 
		registry.addEndpoint("/mickey").withSockJS();
		//WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) { 
		//enables a simple message broker 
		registry.enableSimpleBroker("/topic"); 
		//assures that all messages will be received on the server based on the prefix
		registry.setApplicationDestinationPrefixes("/app"); 
		//WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}
}
