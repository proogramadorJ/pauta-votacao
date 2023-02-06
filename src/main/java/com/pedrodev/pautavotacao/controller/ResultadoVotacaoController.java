package com.pedrodev.pautavotacao.controller;

import com.pedrodev.pautavotacao.model.dto.ResultadoVotacaoDTO;
import com.pedrodev.pautavotacao.service.ResultadoVotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("v1/votacao-resultado")
public class ResultadoVotacaoController {

    private final ResultadoVotacaoService resultadoVotacaoService;

    private static Logger logger = LoggerFactory.getLogger(ResultadoVotacaoController.class);

    public ResultadoVotacaoController(ResultadoVotacaoService resultadoVotacaoService) {
        this.resultadoVotacaoService = resultadoVotacaoService;
    }

    @GetMapping("/{pautaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultadoVotacaoDTO getVoteResult(@PathVariable(value = "pautaId") @NotBlank Long pautaId){
         logger.debug("Request to get vote result on Pauta {}", pautaId);
         return resultadoVotacaoService.getResultByPautaId(pautaId);
    }
}
