package com.cbf.managementsystemconsumerapi.services;

import com.cbf.managementsystemconsumerapi.config.broker.ConsumerService;
import com.cbf.managementsystemconsumerapi.domain.Team;
import com.cbf.managementsystemconsumerapi.dtos.TeamDTO;
import com.cbf.managementsystemconsumerapi.exceptions.BusinessRuleException;
import com.cbf.managementsystemconsumerapi.repositories.TeamRepository;
import com.cbf.managementsystemconsumerapi.util.Constants;
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
