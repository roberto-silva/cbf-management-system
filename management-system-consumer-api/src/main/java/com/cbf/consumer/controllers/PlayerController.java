package com.cbf.consumer.controllers;

import com.cbf.consumer.dtos.PlayerDTO;
import com.cbf.consumer.services.PlayerService;
import com.cbf.consumer.util.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerController {
    @Autowired
    private PlayerService service;

    @RabbitListener(queues = Constants.PLAYER)
    public void consumer(Message message) {
        JsonObject jsonObject = new JsonParser().parse(new String(message.getBody())).getAsJsonObject();
        PlayerDTO playerDTO = new Gson().fromJson(jsonObject.get("object"), PlayerDTO.class);
        String event = new Gson().fromJson(jsonObject.get("event"), String.class);
        if (event.equals(Constants.SAVE_PLAYER)) {
            this.service.save(playerDTO);
        }

    }

}
