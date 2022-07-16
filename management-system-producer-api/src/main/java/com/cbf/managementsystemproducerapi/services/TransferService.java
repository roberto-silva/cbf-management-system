package com.cbf.managementsystemproducerapi.services;

import com.cbf.managementsystemproducerapi.config.broker.ProducerAMQPService;
import com.cbf.managementsystemproducerapi.dtos.TransferDTO;
import com.cbf.managementsystemproducerapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    private ProducerAMQPService<TransferDTO> service;

    public void save(TransferDTO transferDTO) {
        this.service.sendToRabbit(transferDTO, Constants.TRANSFER, Constants.SAVE_TRANSFER);
    }

}
