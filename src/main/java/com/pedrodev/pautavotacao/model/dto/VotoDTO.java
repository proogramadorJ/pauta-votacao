package com.pedrodev.pautavotacao.model.dto;

import com.pedrodev.pautavotacao.model.enumeration.TipoVoto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VotoDTO {

    @NotEmpty(message = "Campo usuário é obrigatório")
    private Long userId;

    @NotEmpty(message = "Campo pauta é obrigatório")
    private Long pautaId;

    @NotEmpty(message = "Campo tipo do voto é obrigatório")
    private TipoVoto tipoVoto;
}
