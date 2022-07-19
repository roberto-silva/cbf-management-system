package com.cbf.producer.services;

import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.util.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
}
