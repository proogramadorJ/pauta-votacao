package com.pedrodev.pautavotacao.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
@Slf4j
public class ResultadoVotacaoConsumer {

    @JmsListener(destination = "resultadovotacaoTopic")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData = null;
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("Resultado votação recebido: "+messageData);
        }
    }
}