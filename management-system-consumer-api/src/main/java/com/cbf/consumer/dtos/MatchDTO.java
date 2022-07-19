package com.cbf.consumer.dtos;

import com.cbf.consumer.core.dtos.ModelDTO;
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

    private String name;

    private LocalDate date;

    private TeamDTO teamOne;

    private TeamDTO teamTwo;

}
