package com.cbf.producer.controllers;

import com.cbf.producer.domain.Team;
import com.cbf.producer.domain.Tournament;
import com.cbf.producer.dtos.MatchAdditionalTimeDTO;
import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.dtos.TournamentDTO;
import com.cbf.producer.services.TournamentService;
import com.cbf.producer.util.RequestUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("v1/api")
@RequestMapping("/tournaments")
@AllArgsConstructor
public class TournamentController {

    private TournamentService service;

    @PostMapping
    public ResponseEntity<TournamentDTO> save(@Valid @RequestBody TournamentDTO tournamentDTO) {
        Tournament tournament = this.service.save(tournamentDTO);
        return ResponseEntity.created(RequestUtil.toURI(tournament.getId())).body(new TournamentDTO(tournament));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TournamentDTO> update(@PathVariable Long id, @Valid @RequestBody TournamentDTO tournamentDTO) {
        Tournament tournament = this.service.update(id, tournamentDTO);
        return ResponseEntity.ok().body(new TournamentDTO(tournament));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TournamentDTO> findById(@PathVariable Long id) {
        Tournament tournament = this.service.getById(id);
        return ResponseEntity.ok().body(new TournamentDTO(tournament));
    }

    @GetMapping()
    public ResponseEntity<Page<TournamentDTO>> findAll(Pageable pageable) {
        Page<TournamentDTO> tournaments = this.service.getAll(pageable);
        return ResponseEntity.ok().body(tournaments);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/matchs/{matchId}/start")
    public ResponseEntity<Void> startMatchById(@PathVariable Long id,
                                               @PathVariable Long matchId,
                                               @Valid @RequestBody TeamDTO teamDTO) {
        this.service.startMatch(id, matchId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/matchs/{matchId}/teams/{teamsId}/gol")
    public ResponseEntity<Void> golInMatch(@PathVariable Long id,
                                           @PathVariable Long matchId,
                                           @PathVariable Long teamsId,
                                           @Valid @RequestBody TeamDTO teamDTO) {
        this.service.golInMatch(id, matchId, teamsId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/matchs/{matchId}/break")
    public ResponseEntity<Void> breakMatch(@PathVariable Long id,
                                           @PathVariable Long matchId) {
        this.service.breakMatch(id, matchId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/matchs/{matchId}/add-time")
    public ResponseEntity<Void> addTimeInMatch(@PathVariable Long id,
                                               @PathVariable Long matchId,
                                               @RequestBody MatchAdditionalTimeDTO matchAdditionalTimeDTO) {
        this.service.addTimeInMatch(id, matchId, matchAdditionalTimeDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/matchs/{matchId}/end")
    public ResponseEntity<Void> endMatch(@PathVariable Long id,
                                         @PathVariable Long matchId) {
        this.service.endMatch(id, matchId);
        return ResponseEntity.noContent().build();
    }
}
