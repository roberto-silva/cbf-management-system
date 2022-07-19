package com.cbf.consumer.dtos;

import com.cbf.consumer.core.dtos.ModelDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchStatusDTO extends ModelDTO {

    private MatchDTO match;

    @Builder.Default
    private Boolean inBreak = false;

}
