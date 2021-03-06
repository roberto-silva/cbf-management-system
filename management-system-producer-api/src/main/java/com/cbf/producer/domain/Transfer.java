package com.cbf.producer.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

import static com.cbf.producer.util.Constants.TRANSFER;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@Table(name = TRANSFER)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_team_id")
    private Team originTeam;

    @ManyToOne
    @JoinColumn(name = "destiny_team_id")
    private Team destinyTeam;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "value", nullable = false)
    private Float value;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
