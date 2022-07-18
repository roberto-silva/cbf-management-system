package com.cbf.managementsystemconsumerapi.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.cbf.managementsystemconsumerapi.util.Constants.TOURNAMENT;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TOURNAMENT)
public class Tournament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    @Builder.Default
    private Set<Team> teams = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    @Builder.Default
    private Set<Match> matches = new HashSet<>();

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public void removeMatch(Match match) {
        this.matches.remove(match);
    }

}
