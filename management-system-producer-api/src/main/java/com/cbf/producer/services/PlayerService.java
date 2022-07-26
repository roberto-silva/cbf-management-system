package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Player;
import com.cbf.producer.domain.Team;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class PlayerService {

    private final PlayerRepository repository;

    @Lazy
    private final TeamService teamService;

    @Transactional
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);
        Team team = teamService.getById(playerDTO.getTeamId());
        player = repository.save(player);
        team.addPlayer(player);
        return player;
    }

    @Transactional
    public Player update(Long id, PlayerDTO playerDTO) {
        Player player = getById(id);
        BeanUtils.copyProperties(playerDTO, player);
        return repository.save(player);
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
