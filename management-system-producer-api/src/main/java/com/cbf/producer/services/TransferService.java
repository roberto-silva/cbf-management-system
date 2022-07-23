package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Transfer;
import com.cbf.producer.dtos.TransferDTO;
import com.cbf.producer.repositories.TransferRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    @Transactional
    public Transfer save(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(transferDTO, transfer);
        return repository.save(transfer);
    }

    @Transactional
    public Transfer update(Long id, TransferDTO transferDTO) {
        Transfer transfer = getById(id);
        BeanUtils.copyProperties(transferDTO, transfer);
        transfer = repository.save(transfer);
        return transfer;
    }

    public Transfer getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Transfer not found."));
    }

    public Page<TransferDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(TransferDTO::new);
    }

    public void delete(Long id) {
        Transfer transfer = getById(id);
        repository.delete(transfer);
    }

}
