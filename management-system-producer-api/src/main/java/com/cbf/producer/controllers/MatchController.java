package com.cbf.producer.controllers;

import com.cbf.producer.domain.Match;
import com.cbf.producer.dtos.MatchDTO;
import com.cbf.producer.services.MatchService;
import com.cbf.producer.util.RequestUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("v1/api")
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService service;

    @PostMapping
    public ResponseEntity<MatchDTO> save(@Valid @RequestBody MatchDTO matchDTO) {
        Match match = this.service.save(matchDTO);
        return ResponseEntity.created(RequestUtil.toURI(match.getId())).body(new MatchDTO(match));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MatchDTO> update(@PathVariable Long id, @Valid @RequestBody MatchDTO matchDTO) {
        Match match = this.service.update(id, matchDTO);
        return ResponseEntity.ok().body(new MatchDTO(match));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MatchDTO> findById(@PathVariable Long id) {
        Match match = this.service.getById(id);
        return ResponseEntity.ok().body(new MatchDTO(match));
    }

    @GetMapping()
    public ResponseEntity<Page<MatchDTO>> findAll(Pageable pageable) {
        Page<MatchDTO> matchs = this.service.getAll(pageable);
        return ResponseEntity.ok().body(matchs);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
