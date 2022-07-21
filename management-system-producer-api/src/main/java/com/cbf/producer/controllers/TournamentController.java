package com.cbf.producer.controllers;

import com.cbf.producer.domain.Tournament;
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
}
