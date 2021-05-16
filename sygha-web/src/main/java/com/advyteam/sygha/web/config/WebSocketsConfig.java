package com.advyteam.sygha.web.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.logging.Logger;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketsConfig implements WebSocketMessageBrokerConfigurer {
    private final static Logger LOGGER = Logger.getLogger(WebSocketsConfig.class.getName());


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        LOGGER.info(registry.toString());
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
        LOGGER.info(registry.toString());

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("api");
        registry.enableSimpleBroker("/topic");
        LOGGER.info(registry.toString());

    }
}
