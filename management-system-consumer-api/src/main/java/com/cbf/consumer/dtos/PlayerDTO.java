package com.cbf.consumer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String country;

    private Long teamId;
}
