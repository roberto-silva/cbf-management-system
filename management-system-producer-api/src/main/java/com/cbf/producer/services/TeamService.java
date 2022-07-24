package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Player;
import com.cbf.producer.domain.Team;
import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Lazy
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class TeamService {

    private final TeamRepository repository;

    private final PlayerService playerService;

    @Transactional
    public Team save(TeamDTO teamDTO) {
        Team team = new Team();
        BeanUtils.copyProperties(teamDTO, team);
        Set<Player> players = teamDTO.getPlayers().stream()
                .map(playerDTO -> playerService.getById(playerDTO.getId()))
                .collect(Collectors.toSet());
        team.setPlayers(players);
        return repository.save(team);
    }

    @Transactional
    public Team update(Long id, TeamDTO teamDTO) {
        Team team = getById(id);
        BeanUtils.copyProperties(teamDTO, team);
        Set<Player> players = teamDTO.getPlayers().stream()
                .map(playerDTO -> playerService.getById(playerDTO.getId()))
                .collect(Collectors.toSet());
        team.setPlayers(players);
        team = repository.save(team);
        return team;
    }

    public Team getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Team not found."));
    }

    public Page<TeamDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(TeamDTO::new);
    }

    public void delete(Long id) {
        Team team = getById(id);
        repository.delete(team);
    }

}
