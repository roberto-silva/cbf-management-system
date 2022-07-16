package com.cbf.managementsystemconsumerapi.config.broker;

import com.cbf.managementsystemconsumerapi.core.dtos.ModelDTO;
import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumerDTO<T extends ModelDTO> {

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
