package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    private Team teamOne;

    private Team teamTwo;

    private Integer teamOneScore;

    private Integer teamTwoScore = 0;
}
