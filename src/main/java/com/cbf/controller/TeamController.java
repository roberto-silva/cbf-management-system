package com.cbf.controller;

import com.cbf.config.broker.RabbitMQService;
import com.cbf.config.constantes.RabbitMQConstants;
import com.cbf.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "teams")
public class TeamController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    private ResponseEntity save(@RequestBody Team team) {
        System.out.println(team);
        this.rabbitMQService.sendMessage(RabbitMQConstants.TEAM_QUEUE, team);
        return new ResponseEntity(HttpStatus.OK);
    }

}
