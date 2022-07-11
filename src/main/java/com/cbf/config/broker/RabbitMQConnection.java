package com.cbf.config.broker;

import com.cbf.config.constantes.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String EXCHANGE_NAME = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange changeDirect() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relationship(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void startingRabbitMQ() {
        DirectExchange exchange = this.changeDirect();

        this.createConnections(RabbitMQConstants.TEAM_QUEUE, exchange);
        this.createConnections(RabbitMQConstants.PLAYER_QUEUE, exchange);
        this.createConnections(RabbitMQConstants.TRANSFER_QUEUE, exchange);
    }

    private void createConnections(String nameQueue, DirectExchange exchange) {
        Queue queue = this.queue(nameQueue);
        Binding connection = this.relationship(queue, exchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareBinding(connection);
    }
}
