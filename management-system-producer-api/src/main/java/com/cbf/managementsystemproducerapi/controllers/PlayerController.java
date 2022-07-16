package com.cbf.managementsystemproducerapi.controllers;

import com.cbf.managementsystemproducerapi.dtos.PlayerDTO;
import com.cbf.managementsystemproducerapi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("/api")
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO playerDTO) {
        this.service.save(playerDTO);
        return ResponseEntity.ok(playerDTO);
    }
}
