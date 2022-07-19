package com.cbf.producer.controllers;

import com.cbf.producer.domain.Player;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.services.PlayerService;
import com.cbf.producer.util.RequestUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("/api")
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService service;

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO playerDTO) {
        Player player = this.service.save(playerDTO);
        return ResponseEntity.created(RequestUtil.toURI(player.getId())).body(new PlayerDTO(player));
    }
}
