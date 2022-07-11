package com.cbf.config.broker;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SuperBuilder
@NoArgsConstructor

public class RabbitMQDto implements Serializable {

    private String events;

    private Object value;

    RabbitMQDto(String events, Object value) {
        this.events = events;
        this.value = value;
    }

    public String getEvents() {
        return events;
    }

    public Object getValue() {
        return value;
    }
}
