package com.cbf.managementsystemproducerapi.config.broker;

import com.cbf.managementsystemproducerapi.util.Constants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class ProducerAMQPConfig {

    private AmqpAdmin amqpAdmin;

    public ProducerAMQPConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange changeDirect() {
        return new DirectExchange(Constants.EXCHANGE_NAME);
    }

    private Binding relationship(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void startingRabbitMQ() {
        DirectExchange exchange = this.changeDirect();

        this.createConnections(Constants.TEAM, exchange);
        this.createConnections(Constants.PLAYER, exchange);
        this.createConnections(Constants.TRANSFER, exchange);
    }

    private void createConnections(String nameQueue, DirectExchange exchange) {
        Queue queue = this.queue(nameQueue);
        Binding connection = this.relationship(queue, exchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareBinding(connection);
    }

}
