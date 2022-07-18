package com.cbf.consumer.config.broker;

import com.cbf.consumer.core.dtos.AbstractDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumerDTO<T extends AbstractDTO> {

    private T object;

    private String event;

    public ConsumerDTO(T object, String event) {
        this.object = object;
        this.event = event;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
