package com.cbf.producer.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

    private String fieldName;

    private String message;

}
