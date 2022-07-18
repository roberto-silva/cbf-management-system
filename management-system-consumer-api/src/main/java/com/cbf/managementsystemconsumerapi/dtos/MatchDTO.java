package com.cbf.managementsystemconsumerapi.dtos;

import com.cbf.managementsystemconsumerapi.core.dtos.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchDTO extends AbstractDTO {

    private Long id;

    private String name;

    private LocalDate date;

    private TeamDTO teamOne;

    private TeamDTO teamTwo;

}
