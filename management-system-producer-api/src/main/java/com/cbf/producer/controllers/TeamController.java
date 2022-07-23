package com.cbf.producer.controllers;

import com.cbf.producer.domain.Team;
import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.services.TeamService;
import com.cbf.producer.util.RequestUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("v1/api/teams")
public class TeamController {
    @Autowired
    private TeamService service;

    @PostMapping
    public ResponseEntity<TeamDTO> save(@Valid @RequestBody TeamDTO teamDTO) {
        Team team = this.service.save(teamDTO);
        return ResponseEntity.created(RequestUtil.toURI(team.getId())).body(new TeamDTO(team));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @Valid @RequestBody TeamDTO teamDTO) {
        Team team = this.service.update(id, teamDTO);
        return ResponseEntity.ok().body(new TeamDTO(team));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id) {
        Team team = this.service.getById(id);
        return ResponseEntity.ok().body(new TeamDTO(team));
    }

    @GetMapping()
    public ResponseEntity<Page<TeamDTO>> findAll(Pageable pageable) {
        Page<TeamDTO> teams = this.service.getAll(pageable);
        return ResponseEntity.ok().body(teams);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
