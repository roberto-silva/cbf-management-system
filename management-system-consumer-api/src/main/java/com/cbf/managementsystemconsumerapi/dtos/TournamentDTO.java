package com.cbf.managementsystemconsumerapi.dtos;

import com.cbf.managementsystemconsumerapi.core.dtos.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TournamentDTO extends AbstractDTO {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Set<TeamDTO> teams = new HashSet<>();

    private Set<MatchDTO> matches = new HashSet<>();

}
