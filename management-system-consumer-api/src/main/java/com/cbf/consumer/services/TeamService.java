package com.cbf.consumer.services;

import com.cbf.consumer.config.broker.ConsumerService;
import com.cbf.consumer.domain.Team;
import com.cbf.consumer.dtos.TeamDTO;
import com.cbf.consumer.exceptions.BusinessRuleException;
import com.cbf.consumer.repositories.TeamRepository;
import com.cbf.consumer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private ConsumerService<TeamDTO> service;

    @Autowired
    private TeamRepository repository;

    public void save(TeamDTO teamDTO) {

        Optional<Team> optional = this.repository.findByName(teamDTO.getName());
        if (optional.isPresent()) {
            throw new BusinessRuleException("This team is exist!");
        }


        this.service.sendMessageToWebSocket(Constants.SAVE_TEAM, teamDTO);
    }
}
