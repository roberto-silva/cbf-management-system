package com.cbf.producer.config.broker;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerAMQPService<T extends ModelDTO> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToRabbit(T modelDto, String queue, String event) {
        try {
            ProducerDTO producerDTO = new ProducerDTO(modelDto, event);
            String json = new ObjectMapper().writeValueAsString(producerDTO);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, queue, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
