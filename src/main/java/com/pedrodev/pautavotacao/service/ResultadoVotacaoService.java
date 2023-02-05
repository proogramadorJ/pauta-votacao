package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.ResultadoVotacaoDTO;
import com.pedrodev.pautavotacao.model.entity.Voto;
import com.pedrodev.pautavotacao.model.enumeration.ResultadoVotacaoDescricao;
import com.pedrodev.pautavotacao.model.enumeration.TipoVoto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pedrodev.pautavotacao.model.enumeration.ResultadoVotacaoDescricao.*;

@Service
public class ResultadoVotacaoService {

    private final PautaService pautaService;
    private final SessaoVotacaoService sessaoVotacaoService;
    private final VotoService votoService;

    public ResultadoVotacaoService(PautaService pautaService, SessaoVotacaoService sessaoVotacaoService, VotoService votoService) {
        this.pautaService = pautaService;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.votoService = votoService;
    }

    public ResultadoVotacaoDTO getResultByPautaId(Long pautaId) {
        validaResultadoVotacao(pautaId);
        List<Voto> votos = votoService.findAllByPautaId(pautaId);

        //TODO verificar se é possivel obter ambos os tipos de votos com apenas uma iteração na lista de votos
       Long votosSim =  votos.stream()
                .filter(voto -> voto.getTipoVoto() == TipoVoto.SIM)
                .count();

       Long votosNao =  votos.stream()
                .filter(voto -> voto.getTipoVoto() == TipoVoto.NAO)
                .count();

        ResultadoVotacaoDescricao resultadoVotacaoDescricao = votosSim > votosNao ? PAUTA_APROVADA : votosNao > votosSim ? PAUTA_REJEITADA : EMPATE;

       return ResultadoVotacaoDTO.builder()
               .pautaId(pautaId)
               .votosSim(votosSim)
               .votosNao(votosNao)
               .resultadoVotacaoDescricao(resultadoVotacaoDescricao)
               .build();
    }

    private void validaResultadoVotacao(Long pautaId) {
        if(!pautaService.isPautaExists(pautaId)){
            throw new BadRequestException("error.resultadovotacao.pauta-nao-econtrada", pautaId);
        }
        if(!sessaoVotacaoService.hasSessionByPautaId(pautaId)){
            throw new BadRequestException("error.resultadovotacao.sessao-nao-econtrada", pautaId);
        }
        if(sessaoVotacaoService.hasOpenSessionByPautaId(pautaId)){
            throw new BadRequestException("error.resultadovotacao.sessao-em-andamento", pautaId);
        }
    }
}