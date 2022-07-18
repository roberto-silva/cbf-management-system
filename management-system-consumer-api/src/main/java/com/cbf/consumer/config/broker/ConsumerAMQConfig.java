package com.cbf.consumer.config.broker;

import com.cbf.consumer.util.Constants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerAMQConfig {

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(Constants.EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Queue declarePlayerQueue() {
        return QueueBuilder.durable(Constants.PLAYER).build();
    }

    @Bean
    public Queue declareTeamQueue() {
        return QueueBuilder.durable(Constants.TEAM).build();
    }

    @Bean
    public Queue declareTransferQueue() {
        return QueueBuilder.durable(Constants.TRANSFER).build();
    }

    @Bean
    public Binding declarePlayerBinding(Exchange exchange, Queue declarePlayerQueue) {
        return BindingBuilder.bind(declarePlayerQueue)
                .to(exchange)
                .with("/api/players")
                .noargs();
    }

    @Bean
    public Binding declareTeamsBinding(Exchange exchange, Queue declareTeamQueue) {
        return BindingBuilder.bind(declareTeamQueue)
                .to(exchange)
                .with("/api/teams")
                .noargs();
    }

    @Bean
    public Binding declareTransfersBinding(Exchange exchange, Queue declareTransferQueue) {
        return BindingBuilder.bind(declareTransferQueue)
                .to(exchange)
                .with("/api/transfers")
                .noargs();
    }
}
