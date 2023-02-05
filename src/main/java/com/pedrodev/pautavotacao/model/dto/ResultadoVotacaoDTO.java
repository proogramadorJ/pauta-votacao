package com.pedrodev.pautavotacao.model.dto;

import com.pedrodev.pautavotacao.model.enumeration.ResultadoVotacaoDescricao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResultadoVotacaoDTO {

    private Long pautaId;
    private Long votosSim;
    private Long votosNao;
    private ResultadoVotacaoDescricao resultadoVotacaoDescricao;
}