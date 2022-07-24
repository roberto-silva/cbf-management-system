package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayerDTO extends ModelDTO {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String country;

    private Long teamId;

    public PlayerDTO(Player player) {
       this.id = player.getId();
       this.name = player.getName();
       this.birthDate = player.getBirthDate();
       this.country = player.getCountry();
    }
}
