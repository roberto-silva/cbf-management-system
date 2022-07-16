package com.cbf.managementsystemconsumerapi.controllers;

import com.cbf.managementsystemconsumerapi.dtos.TeamDTO;
import com.cbf.managementsystemconsumerapi.services.TeamService;
import com.cbf.managementsystemconsumerapi.util.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
public class TeamController {

    @Autowired
    private TeamService service;

    @RabbitListener(queues = Constants.TEAM)
    public void consumer(Message message) {
        JsonObject jsonObject = new JsonParser().parse(new String(message.getBody())).getAsJsonObject();
        TeamDTO teamDTO = new Gson().fromJson(jsonObject.get("object"), TeamDTO.class);
        String event = new Gson().fromJson(jsonObject.get("event"), String.class);
        if (event.equals(Constants.SAVE_TEAM)) {
            this.service.save(teamDTO);
        }

    }
}
