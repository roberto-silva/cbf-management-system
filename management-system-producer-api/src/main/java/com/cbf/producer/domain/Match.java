package com.cbf.producer.domain;

import com.cbf.producer.domain.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static com.cbf.producer.util.Constants.MATCH;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@Table(name = MATCH)
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Builder.Default
    @Column(name = "team_one_score", nullable = false)
    private Integer teamOneScore = 0;

    @Builder.Default
    @Column(name = "team_two_score", nullable = false)
    private Integer teamTwoScore = 0;

    @Builder.Default
    @Column(name = "status", nullable = false)
    private Status status = Status.NOT_STARTED;

    @Builder.Default
    @Column(name = "time", nullable = false)
    private Double time = 90.00;

}
