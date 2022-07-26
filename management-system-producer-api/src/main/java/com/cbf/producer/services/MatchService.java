package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Match;
import com.cbf.producer.domain.Team;
import com.cbf.producer.dtos.MatchDTO;
import com.cbf.producer.repositories.MatchRepository;
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
public class MatchService {

    private MatchRepository repository;
    private TeamService teamService;

    @Transactional
    public Match save(MatchDTO matchDTO) {
        Match match = new Match();
        BeanUtils.copyProperties(matchDTO, match);
        Team teamOne = teamService.getById(matchDTO.getTeamOne().getId());
        Team teamTwo = teamService.getById(matchDTO.getTeamTwo().getId());
        match.setTeamOne(teamOne);
        match.setTeamTwo(teamTwo);
        return repository.save(match);
    }

    @Transactional
    public Match update(Long id, MatchDTO matchDTO) {
        Match match = getById(id);
        BeanUtils.copyProperties(matchDTO, match);
        match = repository.save(match);
        return match;
    }

    public Match getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Match not found."));
    }

    public Page<MatchDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(MatchDTO::new);
    }

    public void delete(Long id) {
        Match match = getById(id);
        repository.delete(match);
    }

}
