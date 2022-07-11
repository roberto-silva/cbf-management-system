package com.cbf.config.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class CustomErrorHandle implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        String nomeDaFila = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println(nomeDaFila);

        String mensagem = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(mensagem);

        System.out.println(t.getCause().getMessage());

        throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila");
    }
}