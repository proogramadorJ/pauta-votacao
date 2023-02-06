package com.pedrodev.pautavotacao.components;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class ResultadoVotacaoConsumer {

    @JmsListener(destination = "resultadovotacaoTopic")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException, JMSException {
        String messageData = null;
        System.out.println("Received message in 2nd topic " + jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            System.out.println("messageData in 2nd listener:"+messageData);
        }
    }
}
