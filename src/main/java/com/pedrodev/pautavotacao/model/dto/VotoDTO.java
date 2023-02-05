package com.pedrodev.pautavotacao.model.dto;

import com.pedrodev.pautavotacao.model.enumeration.TipoVoto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VotoDTO {

    @NotNull(message = "Campo usuário é obrigatório")
    private Long userId;

    @NotNull(message = "Campo pauta é obrigatório")
    private Long pautaId;

    @NotNull(message = "Campo tipo do voto é obrigatório")
    private TipoVoto tipoVoto;
}