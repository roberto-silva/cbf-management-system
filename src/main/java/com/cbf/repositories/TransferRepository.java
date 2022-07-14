package com.cbf.repositories;

import com.cbf.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
