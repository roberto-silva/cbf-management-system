package com.cbf.producer.controllers;

import com.cbf.producer.domain.Player;
import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.services.PlayerService;
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
@RequestMapping("v1/api/players")
public class PlayerController {
    @Autowired
    private PlayerService service;

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@Valid @RequestBody PlayerDTO playerDTO) {
        Player player = this.service.save(playerDTO);
        return ResponseEntity.created(RequestUtil.toURI(player.getId())).body(new PlayerDTO(player));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
        Player player = this.service.update(id, playerDTO);
        return ResponseEntity.ok().body(new PlayerDTO(player));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable Long id) {
        Player player = this.service.getById(id);
        return ResponseEntity.ok().body(new PlayerDTO(player));
    }

    @GetMapping()
    public ResponseEntity<Page<PlayerDTO>> findAll(Pageable pageable) {
        Page<PlayerDTO> players = this.service.getAll(pageable);
        return ResponseEntity.ok().body(players);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
