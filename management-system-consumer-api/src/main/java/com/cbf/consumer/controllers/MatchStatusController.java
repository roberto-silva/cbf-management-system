package com.cbf.consumer.controllers;

import com.cbf.consumer.dtos.MatchDTO;
import com.cbf.consumer.services.MatchStatusService;
import com.cbf.consumer.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@Controller
public class MatchStatusController {

    @Autowired
    private MatchStatusService service;

    @RabbitListener(queues = Constants.STATUS_QUEUE)
    public void consumer(Message message) throws IOException, InterruptedException {
        this.service.sendMessageToWebSocket(new String(message.getBody()));
    }
}
