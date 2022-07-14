package com.cbf.controllers.broker;

import com.cbf.config.sockets.TeamWebSocketConfig;
import com.cbf.util.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class TeamBrokerController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = Constants.TEAM)
    public void consumer(Message message) {
        System.out.println("Message: " + message);
        simpMessagingTemplate.convertAndSend(TeamWebSocketConfig.BROKER,
                new String(message.getBody()));
    }

}
