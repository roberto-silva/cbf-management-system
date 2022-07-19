package com.cbf.producer.services;

import com.cbf.producer.config.broker.ProducerAMQPService;
import com.cbf.producer.domain.Player;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.repositories.PlayerRepository;
import com.cbf.producer.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PlayerService {

    private ProducerAMQPService<PlayerDTO> queueService;
    private PlayerRepository playerRepository;

    public Player save(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);
        player = playerRepository.save(player);
        sendToQueue(playerDTO);
        return player;
    }

    public Player update(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);
        player = playerRepository.save(player);
        sendToQueue(playerDTO);
        return player;
    }

    public Player getById(Long id) {
        return playerRepository.findById(id).orElseThrow();
    }

    private void sendToQueue(PlayerDTO playerDTO) {
        this.queueService.sendToRabbit(playerDTO, Constants.PLAYER, Constants.SAVE_PLAYER);
    }

}
