package com.cbf.consumer.dtos;

import com.cbf.consumer.core.dtos.ModelDTO;
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
}
