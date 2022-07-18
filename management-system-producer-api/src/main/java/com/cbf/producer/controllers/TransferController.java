package com.cbf.producer.controllers;

import com.cbf.producer.dtos.TransferDTO;
import com.cbf.producer.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("/api")
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService service;

    @PostMapping
    public ResponseEntity<TransferDTO> save(@RequestBody TransferDTO transferDTO) {
        this.service.save(transferDTO);
        return ResponseEntity.ok(transferDTO);
    }
}
