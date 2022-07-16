package com.cbf.managementsystemconsumerapi.services;

import com.cbf.managementsystemconsumerapi.config.broker.ConsumerService;
import com.cbf.managementsystemconsumerapi.domain.Transfer;
import com.cbf.managementsystemconsumerapi.dtos.TransferDTO;
import com.cbf.managementsystemconsumerapi.exceptions.BusinessRuleException;
import com.cbf.managementsystemconsumerapi.repositories.TransferRepository;
import com.cbf.managementsystemconsumerapi.util.Constants;
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
