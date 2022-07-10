package com.cbf.config.connections;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    private static final String EXCHANGE_NAME = "amq.direct";

    private Queue queue(String nameQueue) {
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange changeDirect() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relationship(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.EXCHANGE, directExchange.getName(),
                queue.getName(), null);
    }

    private void add(String nameQueue) {
        this.queue();
    }
}
