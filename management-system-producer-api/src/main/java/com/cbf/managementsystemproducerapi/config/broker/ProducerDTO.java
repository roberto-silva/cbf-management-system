package com.cbf.managementsystemproducerapi.config.broker;

import com.cbf.managementsystemproducerapi.core.dtos.ModelDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProducerDTO<T extends ModelDTO> {

    private T object;

    private String event;

    public ProducerDTO(T object, String event) {
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
