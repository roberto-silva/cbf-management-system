package com.cbf.consumer.config.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService<T> {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessageToWebSocket(String socket, T message) {
        simpMessagingTemplate.convertAndSend(socket, message);
    }
}
