package com.pedrodev.pautavotacao.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class PautaDTO {

    private Long id;

    @NotEmpty(message = "Título da pauta não pode ser vazio")
    @Size(min = 3, max = 500, message = "Título da pauta deve ter pelo menos 3 caractes")
    private String titulo;

    @NotEmpty(message = "Descrição da pauta não pode ser vazia")
    @Size(min = 3, message = "Descrição da pauta deve ter pelo menos 3 caractes")
    private String descricao;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

}
