package com.cbf.producer.services;

import com.cbf.producer.config.broker.ProducerAMQPService;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PlayerService {

    @Autowired
    private ProducerAMQPService<PlayerDTO> service;

    public void save(PlayerDTO playerDTO) {
        this.service.sendToRabbit(playerDTO, Constants.PLAYER, Constants.SAVE_PLAYER);
    }

}
