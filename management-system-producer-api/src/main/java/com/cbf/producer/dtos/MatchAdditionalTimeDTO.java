package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchAdditionalTimeDTO extends ModelDTO {
    private Long id;
    private Double additionalTime;
}
