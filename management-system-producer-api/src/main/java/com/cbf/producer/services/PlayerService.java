package com.cbf.producer.services;

import com.cbf.producer.domain.Player;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PlayerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private PlayerRepository playerRepository;

    public Player save(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);
        player = playerRepository.save(player);
        rabbitTemplate.convertAndSend(playerDTO);
        return player;
    }

    public Player update(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);
        player = playerRepository.save(player);
        rabbitTemplate.convertAndSend(playerDTO);
        return player;
    }

    public Player getById(Long id) {
        return playerRepository.findById(id).orElseThrow();
    }

}
