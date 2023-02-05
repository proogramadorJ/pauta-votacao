package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.model.entity.SessaoVotacao;
import com.pedrodev.pautavotacao.scheduling.PublicaResultadoVotacaoJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PublicaResultadoVotacaoService {

    private static final String JOB_NAME = "resultadoVotacaoJob";
    private static final String TRIGGER_NAME = "resultadoVotacaoTrigger";
    private static final String GROUP_NAME = "resultadoVotacaoGrupo";

    private static final Logger logger = LoggerFactory.getLogger(PublicaResultadoVotacaoService.class);

    void agendaPublicacaoResultadoVotacao(SessaoVotacao sessaoVotacao){

        JobDataMap jobDataMap =  buildJobData(sessaoVotacao);
        JobDetail jobDetail = buildJobDetail(jobDataMap);
        Trigger jobTrigger = buildJobTrigger(sessaoVotacao.getDataEncerramento());

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, jobTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("Erro ao tentar agendar tarefa "+ e.getMessage());
        }
    }

    private JobDataMap buildJobData(SessaoVotacao sessaoVotacao) {
        JobDataMap jobData = new JobDataMap();
        jobData.put("pautaId", sessaoVotacao.getPauta().getId());
        return jobData;
    }

    private  JobDetail buildJobDetail(JobDataMap jobDataMap) {
        return JobBuilder.newJob(PublicaResultadoVotacaoJob.class)
                .withIdentity(JOB_NAME, GROUP_NAME)
                .usingJobData(jobDataMap)
                .build();
    }

    Trigger buildJobTrigger (LocalDateTime startAt){
       Instant instant = startAt.atZone(ZoneId.systemDefault()).toInstant();
       return  new SimpleTriggerImpl(TRIGGER_NAME,
                GROUP_NAME,
                 Date.from(instant),
                null,
                0,
                0L);
    }
}