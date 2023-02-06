package com.pedrodev.pautavotacao.controller;

import com.pedrodev.pautavotacao.model.dto.VotoDTO;
import com.pedrodev.pautavotacao.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VotoController {

    private final VotoService votoService;
    private static Logger logger = LoggerFactory.getLogger(VotoController.class);

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/v1/voto")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Validated @RequestBody VotoDTO userDTO){
        logger.debug("Request to create a new Voto");
        votoService.createVoto(userDTO);
    }
}