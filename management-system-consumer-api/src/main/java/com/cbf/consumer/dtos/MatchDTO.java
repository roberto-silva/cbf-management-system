package com.cbf.consumer.dtos;

import com.cbf.consumer.core.dtos.ModelDTO;
import com.cbf.consumer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    private Status status;

    private Double time;
}
