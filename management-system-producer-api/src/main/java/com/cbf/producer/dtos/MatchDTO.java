package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Match;
import com.cbf.producer.domain.Team;
import com.cbf.producer.domain.enums.Status;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchDTO extends ModelDTO {

    private Long id;

    private LocalDate date;

    private String country;

    private TeamDTO teamOne;

    private TeamDTO teamTwo;

    private Integer teamOneScore = 0;

    private Integer teamTwoScore = 0;

    private Status status = Status.NOT_STARTED;

    private Double time = 90.00;

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.date = match.getDate();
        this.country = match.getCountry();
        this.teamOne = new TeamDTO(match.getTeamOne());
        this.teamTwo = new TeamDTO(match.getTeamTwo());
        this.teamOneScore = match.getTeamOneScore();
        this.teamTwoScore = match.getTeamTwoScore();
        this.status = match.getStatus();
        this.time = match.getTime();
    }
}
