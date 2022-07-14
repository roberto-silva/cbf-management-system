package com.cbf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocket
@EnableWebSocketMessageBroker
public class CbfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbfApplication.class, args);
    }

}
