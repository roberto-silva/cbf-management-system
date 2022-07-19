package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchStatusDTO extends ModelDTO {

    private MatchDTO match;

    @Builder.Default
    private Boolean inBreak = false;

}
