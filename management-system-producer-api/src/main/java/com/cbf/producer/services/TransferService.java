package com.cbf.producer.services;

import com.cbf.producer.config.broker.ProducerAMQPService;
import com.cbf.producer.dtos.TransferDTO;
import com.cbf.producer.util.Constants;
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
