package com.cbf.managementsystemconsumerapi.controllers;

import com.cbf.managementsystemconsumerapi.dtos.TransferDTO;
import com.cbf.managementsystemconsumerapi.services.TransferService;
import com.cbf.managementsystemconsumerapi.util.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferController {
    @Autowired
    private TransferService service;

    @RabbitListener(queues = Constants.TRANSFER)
    public void consumer(Message message) {
        JsonObject jsonObject = new JsonParser().parse(new String(message.getBody())).getAsJsonObject();
        TransferDTO transferDTO = new Gson().fromJson(jsonObject.get("object"), TransferDTO.class);
        String event = new Gson().fromJson(jsonObject.get("event"), String.class);
        if (event.equals(Constants.SAVE_TRANSFER)) {
            this.service.save(transferDTO);
        }
    }
}