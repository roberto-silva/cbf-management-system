package com.cbf.producer.services;

import com.cbf.producer.config.broker.ProducerAMQPService;
import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private ProducerAMQPService<TeamDTO> service;

    public void save(TeamDTO teamDTO) {
        this.service.sendToRabbit(teamDTO, Constants.TEAM, Constants.SAVE_TEAM);
    }
}
