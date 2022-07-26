package com.cbf.producer.controllers;

import com.cbf.producer.domain.Transfer;
import com.cbf.producer.dtos.TransferDTO;
import com.cbf.producer.services.TransferService;
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
@RequestMapping("v1/api/transfers")
public class TransferController {
    @Autowired
    private TransferService service;

    @PostMapping
    public ResponseEntity<TransferDTO> save(@Valid @RequestBody TransferDTO transferDTO) {
        Transfer transfer = this.service.save(transferDTO);
        return ResponseEntity.created(RequestUtil.toURI(transfer.getId())).body(new TransferDTO(transfer));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> update(@PathVariable Long id, @Valid @RequestBody TransferDTO transferDTO) {
        Transfer transfer = this.service.update(id, transferDTO);
        return ResponseEntity.ok().body(new TransferDTO(transfer));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> findById(@PathVariable Long id) {
        Transfer transfer = this.service.getById(id);
        return ResponseEntity.ok().body(new TransferDTO(transfer));
    }

    @GetMapping()
    public ResponseEntity<Page<TransferDTO>> findAll(Pageable pageable) {
        Page<TransferDTO> transfer = this.service.getAll(pageable);
        return ResponseEntity.ok().body(transfer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
