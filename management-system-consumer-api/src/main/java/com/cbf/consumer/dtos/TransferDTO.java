package com.cbf.consumer.dtos;

import com.cbf.consumer.core.dtos.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransferDTO extends AbstractDTO {

    private Long id;

    private TeamDTO originTeam;

    private TeamDTO destinyTeam;

    private LocalDate date;

    private Float value;
}