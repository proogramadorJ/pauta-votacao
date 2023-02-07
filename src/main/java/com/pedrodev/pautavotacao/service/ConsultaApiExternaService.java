package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.model.dto.ConsultaCpfResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ConsultaApiExternaService {

    private final RestTemplate restTemplate;;

    @Value("${consultacpf.api-url}")
    private String apiCpfUrl;

    public ConsultaApiExternaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity checkIfCpfIsAbleToVote(String cpf) {
        try{
            return restTemplate.getForEntity(apiCpfUrl.concat(cpf), ConsultaCpfResponseDTO.class);
        }catch (Exception error){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}