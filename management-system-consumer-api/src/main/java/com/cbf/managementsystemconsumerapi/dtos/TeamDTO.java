package com.cbf.managementsystemconsumerapi.dtos;

import com.cbf.managementsystemconsumerapi.core.dtos.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamDTO extends AbstractDTO {

    private Long id;

    private String name;

    private String locale;

}
