package com.cbf.producer.controllers;

import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService service;

    @PostMapping
    public ResponseEntity<TeamDTO> save(@RequestBody TeamDTO team) {
        this.service.save(team);
        return ResponseEntity.ok(team);
    }
}
