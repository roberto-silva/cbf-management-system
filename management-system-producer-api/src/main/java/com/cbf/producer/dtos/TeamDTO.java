package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Player;
import com.cbf.producer.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamDTO extends ModelDTO {

    private Long id;

    private String name;

    private String locale;

    private Set<PlayerDTO> players = new HashSet<>();

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.locale = team.getLocale();
        if (!team.getPlayers().isEmpty()) {
            this.players = team.getPlayers().stream().map(PlayerDTO::new)
                    .collect(Collectors.toSet());
        }

    }
}
