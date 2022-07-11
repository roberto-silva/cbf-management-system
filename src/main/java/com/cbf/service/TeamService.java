package com.cbf.service;

import com.cbf.config.broker.RabbitMQDto;
import com.cbf.config.constantes.RabbitMQConstants;
import com.cbf.domain.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TeamService {

    @RabbitListener(queues = RabbitMQConstants.TEAM_QUEUE)
    public void rabbitMQListener(String message) throws JsonProcessingException, InterruptedException {
        try {RabbitMQDto response = new ObjectMapper().readValue(message, RabbitMQDto.class);
            System.out.println(response);
//            if (response.getEvents().equals("save")) {
//                this.saveTeam((Team) response.getValue());
//            }
        } catch (IllegalArgumentException error) {
            throw new IllegalArgumentException("Argumento inválido!");
        }
    }


    public void saveTeam(Team team) {
        System.out.println(team);
    }

}
