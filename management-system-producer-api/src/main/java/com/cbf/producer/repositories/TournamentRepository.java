package com.cbf.producer.repositories;

import com.cbf.producer.domain.Match;
import com.cbf.producer.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
