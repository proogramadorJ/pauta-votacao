package com.pedrodev.pautavotacao.controller;

import com.pedrodev.pautavotacao.service.ConsultaApiExternaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ConsultaApiExternaController {

    private final ConsultaApiExternaService consultaApiExternaService;

    public ConsultaApiExternaController(ConsultaApiExternaService consultaApiExternaService) {
        this.consultaApiExternaService = consultaApiExternaService;
    }

    @GetMapping("/v1/consulta-cpf/{cpf}")
    public ResponseEntity checkIfCpfIsAbleToVote(@PathVariable("cpf") String cpf){
        log.info("Request to check if the CPF {} is able to vote", cpf);
        return consultaApiExternaService.checkIfCpfIsAbleToVote(cpf);
    }

}
