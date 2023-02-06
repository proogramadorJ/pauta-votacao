package com.pedrodev.pautavotacao.controller;

import com.pedrodev.pautavotacao.model.dto.SessaoVotacaoDTO;
import com.pedrodev.pautavotacao.service.SessaoVotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessao-votacao")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;
    private static Logger logger = LoggerFactory.getLogger(SessaoVotacaoController.class);

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoVotacaoDTO createAndStart(@Validated @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO){
        logger.debug("Request to create a new SessaoVotacao");
        return sessaoVotacaoService.createAndStartSessaoVotacao(sessaoVotacaoDTO);
    }
}