package com.cbf.services;

import com.cbf.domain.Team;
import com.cbf.dtos.TeamDTO;
import com.cbf.repositories.TeamRepository;
import com.cbf.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTeamToRabbit(Team team) {
        try {
            String json = new ObjectMapper().writeValueAsString(team);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.TEAM, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Team save(TeamDTO team) {
        Team teamSaved = this.repository.save(new Team(team));
        System.out.println(teamSaved);
        this.sendTeamToRabbit(teamSaved);
        return teamSaved;
    }

}
