package com.cbf.config.broker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String nameQueue, String event, Object message) {
        try {
            RabbitMQDto rabbitMQDto = new RabbitMQDto(event, message);
            String messageJson = this.objectMapper.writeValueAsString(rabbitMQDto);
            this.rabbitTemplate.convertAndSend(nameQueue, messageJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
