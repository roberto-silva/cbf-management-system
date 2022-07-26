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
        return QueueBuilder.durable(Constants.STATUS_QUEUE).build();
    }

    @Bean
    public Binding declarePlayerBinding(Exchange exchange, Queue declarePlayerQueue) {
        return BindingBuilder.bind(declarePlayerQueue)
                .to(exchange)
                .with("/api/players")
                .noargs();
    }
}
