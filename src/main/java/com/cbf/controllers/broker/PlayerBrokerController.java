package com.cbf.controllers.broker;

import com.cbf.config.sockets.PlayerWebSocketConfig;
import com.cbf.util.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class PlayerBrokerController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = Constants.PLAYER)
    public void consumer(Message message) {
        simpMessagingTemplate.convertAndSend(PlayerWebSocketConfig.BROKER,
                new String(message.getBody()));
    }
}
