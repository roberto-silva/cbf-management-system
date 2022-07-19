package com.cbf.producer.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.cbf.producer.util.Constants.TEAM;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TEAM)
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(min = 2, max = 50)
    @Column(name = "locale", nullable = false, length = 100)
    private String locale;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    @Builder.Default
    private Set<Player> teams = new HashSet<>();


}