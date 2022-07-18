package com.cbf.consumer.services;

import com.cbf.consumer.config.broker.ConsumerService;
import com.cbf.consumer.domain.Player;
import com.cbf.consumer.dtos.PlayerDTO;
import com.cbf.consumer.exceptions.BusinessRuleException;
import com.cbf.consumer.repositories.PlayerRepository;
import com.cbf.consumer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private ConsumerService<PlayerDTO> service;

    @Autowired
    private PlayerRepository repository;

    public void save(PlayerDTO playerDTO) {
        Optional<Player> optional = this.repository.findByName(playerDTO.getName());
        if (optional.isPresent()) {
            throw new BusinessRuleException("This player is exist!");
        }
        this.service.sendMessageToWebSocket(Constants.SAVE_PLAYER, playerDTO);
    }
}
