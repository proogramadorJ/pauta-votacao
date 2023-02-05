package com.pedrodev.pautavotacao.scheduling;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicaResultadoVotacaoJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(PublicaResultadoVotacaoJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Publicando resultado votação");
    }
}