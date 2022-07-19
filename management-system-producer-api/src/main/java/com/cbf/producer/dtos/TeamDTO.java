package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamDTO extends ModelDTO {

    private Long id;

    private String name;

    private String locale;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.locale = team.getLocale();
    }
}
