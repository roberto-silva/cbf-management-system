package com.cbf.producer.services;

import com.cbf.producer.controllers.exceptions.NotFoundException;
import com.cbf.producer.domain.Tournament;
import com.cbf.producer.dtos.TournamentDTO;
import com.cbf.producer.repositories.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class TournamentService {

    private TournamentRepository repository;

    public Tournament save(TournamentDTO matchDTO) {
        Tournament match = new Tournament();
        BeanUtils.copyProperties(matchDTO, match);
        return repository.save(match);
    }

    public Tournament update(Long id, TournamentDTO matchDTO) {
        Tournament match = getById(id);
        BeanUtils.copyProperties(matchDTO, match);
        match = repository.save(match);
        return match;
    }

    public Tournament getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Tournament not found."));
    }

    public Page<TournamentDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(TournamentDTO::new);
    }

    public void delete(Long id) {
        Tournament match = getById(id);
        repository.delete(match);
    }

}
