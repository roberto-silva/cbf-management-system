package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Match;
import com.cbf.producer.domain.Team;
import com.cbf.producer.domain.Tournament;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TournamentDTO extends ModelDTO {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Set<Team> teams = new HashSet<>();

    private Set<Match> matches = new HashSet<>();

    public TournamentDTO(Tournament tournament) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.startDate = tournament.getStartDate();
        this.endDate = tournament.getEndDate();
        this.teams = tournament.getTeams();
        this.matches = tournament.getMatches();
    }

}
