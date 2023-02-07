package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.ResultadoVotacaoDTO;
import com.pedrodev.pautavotacao.model.entity.Voto;
import com.pedrodev.pautavotacao.model.enumeration.ResultadoVotacaoDescricao;
import com.pedrodev.pautavotacao.model.enumeration.TipoVoto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static com.pedrodev.pautavotacao.model.enumeration.ResultadoVotacaoDescricao.*;
import static com.pedrodev.pautavotacao.model.enumeration.TipoVoto.SIM;

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

        AtomicLong votosSim = new AtomicLong(0L);
        AtomicLong votosNao = new AtomicLong(0L);

        votos.forEach( a ->{
            if(a.getTipoVoto() == SIM)
                votosSim.getAndSet(votosSim.get() + 1);
            else
                votosNao.getAndSet(votosNao.get() + 1);
        });

       ResultadoVotacaoDescricao resultadoVotacaoDescricao = votosSim.get() > votosNao.get() ? PAUTA_APROVADA : votosNao.get() > votosSim.get() ? PAUTA_REJEITADA : EMPATE;

       return ResultadoVotacaoDTO.builder()
               .pautaId(pautaId)
               .votosSim(votosSim.get())
               .votosNao(votosNao.get())
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