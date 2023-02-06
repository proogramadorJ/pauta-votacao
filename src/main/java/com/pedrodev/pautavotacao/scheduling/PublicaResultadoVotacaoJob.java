package com.pedrodev.pautavotacao.scheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedrodev.pautavotacao.components.JmsProducer;
import com.pedrodev.pautavotacao.config.StaticApplicationContext;
import com.pedrodev.pautavotacao.model.dto.ResultadoVotacaoDTO;
import com.pedrodev.pautavotacao.service.ResultadoVotacaoService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class PublicaResultadoVotacaoJob implements Job {

    private JmsProducer jmsProducer;
    private ResultadoVotacaoService resultadoVotacaoService;

    private ApplicationContext applicationContext = StaticApplicationContext.getContext();

    private static Logger logger = LoggerFactory.getLogger(PublicaResultadoVotacaoJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        preExecute();
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        Long pautaId = data.getLong("pautaId");
        ResultadoVotacaoDTO resultadoVotacaoDTO = resultadoVotacaoService.getResultByPautaId(pautaId);
        logger.info("Publicando resultado votação");
        try {
            jmsProducer.sendMessageToTopic(resultadoVotacaoDTO);
            logger.info("Resultado da votação para a pauta {} publicado com sucesso",pautaId);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao tentar publicar resultado da votação para a pauta {} "+e.getMessage(), pautaId);
        }
    }

    private void preExecute() {
        if(resultadoVotacaoService == null || jmsProducer == null ){
            resultadoVotacaoService = applicationContext.getBean(ResultadoVotacaoService.class);
            jmsProducer = applicationContext.getBean(JmsProducer.class);
        }
    }
}