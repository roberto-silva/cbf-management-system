package com.cbf.controllers.simpler;

import com.cbf.domain.Team;
import com.cbf.dtos.TeamDTO;
import com.cbf.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService service;

    @PostMapping
    public ResponseEntity<TeamDTO> save(@RequestBody TeamDTO team) {
        Team teamSaved = this.service.save(team);
        System.out.println(String.format("Team saved: %s", teamSaved.toString()));
        return ResponseEntity.ok(team);
    }
}
