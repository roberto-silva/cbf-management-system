package com.cbf.producer.config.broker;

import com.cbf.producer.dtos.MatchDTO;
import com.cbf.producer.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerAMQPService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToRabbit(MatchDTO matchDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            objectMapper.registerModule(module);
            String json = objectMapper.writeValueAsString(matchDTO);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.STATUS_QUEUE, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}