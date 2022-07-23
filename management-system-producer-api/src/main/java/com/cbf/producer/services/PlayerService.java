package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Player;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PlayerService {
    @Autowired
    private PlayerRepository repository;

    @Transactional
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);
        return repository.save(player);
    }

    @Transactional
    public Player update(Long id, PlayerDTO playerDTO) {
        Player player = getById(id);
        BeanUtils.copyProperties(playerDTO, player);
        player = repository.save(player);
        return player;
    }

    public Player getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Player not found."));
    }

    public Page<PlayerDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(PlayerDTO::new);
    }

    public void delete(Long id) {
        Player player = getById(id);
        repository.delete(player);
    }

}
