package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.BusinessRuleException;
import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Match;
import com.cbf.producer.domain.Team;
import com.cbf.producer.domain.Tournament;
import com.cbf.producer.domain.enums.Status;
import com.cbf.producer.dtos.MatchAdditionalTimeDTO;
import com.cbf.producer.dtos.MatchDTO;
import com.cbf.producer.dtos.TournamentDTO;
import com.cbf.producer.repositories.TournamentRepository;
import com.cbf.producer.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TournamentService {

    private TournamentRepository repository;

    private MatchService matchService;

    private TeamService teamService;

    private RabbitTemplate rabbitTemplate;

    @Transactional
    public Tournament save(TournamentDTO tournamentDTO) {
        Tournament tournament = new Tournament();
        searchAndSetTeamsAndMatches(tournamentDTO, tournament);
        return repository.save(tournament);
    }

    @Transactional
    public Tournament update(Long id, TournamentDTO tournamentDTO) {
        Tournament tournament = getById(id);
        searchAndSetTeamsAndMatches(tournamentDTO, tournament);
        return repository.save(tournament);
    }

    private void searchAndSetTeamsAndMatches(TournamentDTO tournamentDTO, Tournament tournament) {
        BeanUtils.copyProperties(tournamentDTO, tournament);
        Set<Team> teams = tournamentDTO.getTeams().stream()
                .map(team -> teamService.getById(team.getId()))
                .collect(Collectors.toSet());
        Set<Match> matches = tournamentDTO.getMatches().stream()
                .map(match -> matchService.getById(match.getId()))
                .collect(Collectors.toSet());
        tournament.setTeams(teams);
        tournament.setMatches(matches);
    }

    public Tournament getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Tournament not found."));
    }

    public Page<TournamentDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(TournamentDTO::new);
    }

    public void delete(Long id) {
        Tournament match = getById(id);
        repository.delete(match);
    }

    public void startMatch(Long id, Long matchId) {
        Match match = getMatchByTournamentAndMatchId(id, matchId);
        match.setDate(LocalDate.now());
        match = this.matchService.update(id, new MatchDTO(match));
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.STATUS_QUEUE, new MatchDTO(match));
    }

    public void golInMatch(Long id, Long matchId, Long teamId) {
        Match match = getMatchByTournamentAndMatchId(id, matchId);

        if (match.getTeamOne().getId().equals(teamId)) {
            match.setTeamOneScore(match.getTeamOneScore() + 1);
        } else {
            match.setTeamTwoScore(match.getTeamTwoScore() + 1);
        }

        match = this.matchService.update(id, new MatchDTO(match));
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.STATUS_QUEUE, new MatchDTO(match));
    }

    public void breakMatch(Long id, Long matchId) {
        Match match = getMatchByTournamentAndMatchId(id, matchId);
        match.setStatus(Status.BREAK);
        match = this.matchService.update(id, new MatchDTO(match));
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.STATUS_QUEUE, new MatchDTO(match));
    }

    public void endMatch(Long id, Long matchId) {
        Match match = getMatchByTournamentAndMatchId(id, matchId);
        match.setStatus(Status.FINISHED);
        match = this.matchService.update(id, new MatchDTO(match));
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.STATUS_QUEUE, new MatchDTO(match));
    }

    public void addTimeInMatch(Long id, Long matchId, MatchAdditionalTimeDTO matchAdditionalTimeDTO) {
        Match match = getMatchByTournamentAndMatchId(id, matchId);
        match.setTime(match.getTime() + matchAdditionalTimeDTO.getAdditionalTime());
        match = this.matchService.update(id, new MatchDTO(match));
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.STATUS_QUEUE, new MatchDTO(match));
    }

    private Match getMatchByTournamentAndMatchId(Long id, Long matchId) {
        Tournament tournament = getById(id);
        return tournament.getMatches().stream()
                .filter(matchAux -> matchAux.getId().equals(matchId))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Match not found in this tournament."));
    }


}
