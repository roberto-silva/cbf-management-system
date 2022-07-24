package com.cbf.consumer.services;

import com.cbf.consumer.dtos.MatchDTO;
import com.cbf.consumer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MatchStatusService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessageToWebSocket(MatchDTO matchDTO) {
        simpMessagingTemplate.convertAndSend(Constants.STATUS_CHANEL, matchDTO);
    }


}
