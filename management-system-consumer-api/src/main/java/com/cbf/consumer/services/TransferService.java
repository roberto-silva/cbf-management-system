package com.cbf.consumer.services;

import com.cbf.consumer.config.broker.ConsumerService;
import com.cbf.consumer.domain.Transfer;
import com.cbf.consumer.dtos.TransferDTO;
import com.cbf.consumer.exceptions.BusinessRuleException;
import com.cbf.consumer.repositories.TransferRepository;
import com.cbf.consumer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private ConsumerService<TransferDTO> service;

    @Autowired
    private TransferRepository repository;

    public void save(TransferDTO transferDTO) {
        Optional<Transfer> optional = this.repository.findById(transferDTO.getId());
        if (optional.isPresent()) {
            throw new BusinessRuleException("This transfer is exist!");
        }
        this.service.sendMessageToWebSocket(Constants.SAVE_TRANSFER, transferDTO);
    }
}
