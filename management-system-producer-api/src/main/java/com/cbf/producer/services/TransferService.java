package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Player;
import com.cbf.producer.domain.Team;
import com.cbf.producer.domain.Transfer;
import com.cbf.producer.dtos.TeamDTO;
import com.cbf.producer.dtos.TransferDTO;
import com.cbf.producer.repositories.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransferService {

    private TransferRepository repository;

    private TeamService teamService;

    private PlayerService playerService;

    @Transactional
    public Transfer save(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(transferDTO, transfer);
        executeTransferAndPersistValues(transferDTO, transfer);
        return repository.save(transfer);
    }

    @Transactional
    public Transfer update(Long id, TransferDTO transferDTO) {
        Transfer transfer = getById(id);
        BeanUtils.copyProperties(transferDTO, transfer);
        executeTransferAndPersistValues(transferDTO, transfer);
        return repository.save(transfer);
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

    private void executeTransferAndPersistValues(TransferDTO transferDTO, Transfer transfer) {
        Player player = playerService.getById(transferDTO.getPlayer().getId());
        Team originTeam = teamService.getById(transferDTO.getOriginTeam().getId());
        Team destinyTeam = teamService.getById(transferDTO.getDestinyTeam().getId());
        destinyTeam.addPlayer(player);
        destinyTeam = teamService.save(new TeamDTO(destinyTeam));
        originTeam.removePlayer(player);
        originTeam = teamService.save(new TeamDTO(originTeam));
        transfer.setOriginTeam(originTeam);
        transfer.setDestinyTeam(destinyTeam);
        transfer.setPlayer(player);
    }

}
