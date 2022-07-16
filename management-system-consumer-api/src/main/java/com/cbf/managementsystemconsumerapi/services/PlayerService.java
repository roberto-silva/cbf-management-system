package com.cbf.managementsystemconsumerapi.services;

import com.cbf.managementsystemconsumerapi.config.broker.ConsumerService;
import com.cbf.managementsystemconsumerapi.domain.Player;
import com.cbf.managementsystemconsumerapi.dtos.PlayerDTO;
import com.cbf.managementsystemconsumerapi.exceptions.BusinessRuleException;
import com.cbf.managementsystemconsumerapi.repositories.PlayerRepository;
import com.cbf.managementsystemconsumerapi.util.Constants;
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
