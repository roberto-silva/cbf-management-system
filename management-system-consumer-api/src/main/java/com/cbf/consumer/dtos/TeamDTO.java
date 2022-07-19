package com.cbf.consumer.dtos;

import com.cbf.consumer.core.dtos.ModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamDTO extends ModelDTO {

    private Long id;

    private String name;

    private String locale;

}
