package com.pedrodev.pautavotacao.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrodev.pautavotacao.model.dto.ResultadoVotacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
@Slf4j
public class ResultadoVotacaoProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${spring.activemq.topic}")
    String topic;

    public ResultadoVotacaoProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessageToTopic(ResultadoVotacaoDTO resultadoVotacao) throws JsonProcessingException {
        try {
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(resultadoVotacao);
            jmsTemplate.send(topic, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
        }
        catch (Exception ex) {
            log.error("ERROR in sending message to topic "+ex);
        }
    }
}