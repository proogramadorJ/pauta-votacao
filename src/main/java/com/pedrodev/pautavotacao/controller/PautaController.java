package com.pedrodev.pautavotacao.controller;

import com.pedrodev.pautavotacao.model.dto.PautaDTO;
import com.pedrodev.pautavotacao.service.PautaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PautaController {

    private final PautaService pautaService;

    private static Logger logger = LoggerFactory.getLogger(PautaController.class);

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("/v1/pauta")
    @ResponseStatus(HttpStatus.CREATED)
    public PautaDTO create(@Validated @RequestBody PautaDTO pautaDTO){
        logger.debug("Request to create a new Pauta {} ", pautaDTO.getTitulo() );
        return pautaService.createPauta(pautaDTO);
    }

    @PutMapping("/v1/pauta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PautaDTO update(@PathVariable(value = "id") @NotBlank Long id, @Validated @RequestBody PautaDTO pautaDTO){
        logger.debug("Request to update a Pauta {}", pautaDTO.getTitulo());
        return pautaService.updatePauta(id, pautaDTO);
    }

    @GetMapping("/v1/pauta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PautaDTO find(@PathVariable(value = "id") @NotBlank Long id){
        logger.debug("Request to find Pauta {}", id);
        return pautaService.findPautaById(id);
    }

    @GetMapping("/v1/pauta")
    @ResponseStatus(HttpStatus.OK)
    public List<PautaDTO> findAll(){
        logger.debug("Request to find All Pauta");
        return pautaService.findAll();
    }

    @DeleteMapping("/v1/pauta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") @NotBlank Long id){
        logger.debug("Request to delete Pauta {}", id);
        pautaService.deletePauta(id);
    }
}