package com.cbf.managementsystemproducerapi.services;

import com.cbf.managementsystemproducerapi.config.broker.ProducerAMQPService;
import com.cbf.managementsystemproducerapi.dtos.TeamDTO;
import com.cbf.managementsystemproducerapi.util.Constants;
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
