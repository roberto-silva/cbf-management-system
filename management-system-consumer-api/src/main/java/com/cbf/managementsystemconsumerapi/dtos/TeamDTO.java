package com.cbf.managementsystemconsumerapi.dtos;

import com.cbf.managementsystemconsumerapi.core.dtos.ModelDTO;
import com.cbf.managementsystemconsumerapi.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO extends ModelDTO {

    private Long id;

    private String name;

    private String locale;

   public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.locale = team.getLocale();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
