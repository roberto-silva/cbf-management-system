package com.cbf.controller;

import com.cbf.config.broker.RabbitMQService;
import com.cbf.config.constantes.RabbitMQConstants;
import com.cbf.service.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "teams")
public class TeamController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private TeamService teamService;

    @PostMapping
    private void save(@RequestBody Object team) throws JsonProcessingException, InterruptedException {
        this.rabbitMQService.sendMessage(RabbitMQConstants.TEAM_QUEUE, "save", team);
    }

}
