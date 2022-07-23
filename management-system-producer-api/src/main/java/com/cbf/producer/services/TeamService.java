package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Player;
import com.cbf.producer.domain.Team;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.repositories.PlayerRepository;
import com.cbf.producer.repositories.TeamRepository;
import com.cbf.producer.util.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TeamService {
    @Autowired
    private TeamRepository repository;

    @Transactional
    public Team save(TeamDTO teamDTO) {
        Team team = new Team();
        BeanUtils.copyProperties(teamDTO, team);
        return repository.save(team);
    }

    @Transactional
    public Team update(Long id, TeamDTO teamDTO) {
        Team team = getById(id);
        BeanUtils.copyProperties(teamDTO, team);
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
