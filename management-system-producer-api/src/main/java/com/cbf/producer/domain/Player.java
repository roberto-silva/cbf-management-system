package com.cbf.producer.domain;

import com.cbf.producer.dtos.PlayerDTO;
import com.cbf.producer.dtos.TeamDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static com.cbf.producer.util.Constants.PLAYER;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@Table(name = PLAYER)
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    public Player(PlayerDTO playerDTO) {
        this.id = playerDTO.getId();
        this.name = playerDTO.getName();
        this.birthDate = playerDTO.getBirthDate();
        this.country = playerDTO.getCountry();
    }
}
