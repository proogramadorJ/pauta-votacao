package com.pedrodev.pautavotacao.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class SessaoVotacaoDTO {

    private Long id;

    @NotNull(message = "Pauta não pode ser vazia")
    private PautaDTO pauta;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAbertura;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEncerramento;
}