package com.cbf.consumer.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static com.cbf.consumer.util.Constants.MATCH;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = MATCH)
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @ManyToOne
    @JoinColumn(name = "team_one_id")
    private Team teamOne;

    @ManyToOne
    @JoinColumn(name = "team_two_id")
    private Team teamTwo;

}
