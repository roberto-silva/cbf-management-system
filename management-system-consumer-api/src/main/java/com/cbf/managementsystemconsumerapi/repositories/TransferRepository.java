package com.cbf.managementsystemconsumerapi.repositories;

import com.cbf.managementsystemconsumerapi.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Optional<Transfer> findById(Long id);
}
