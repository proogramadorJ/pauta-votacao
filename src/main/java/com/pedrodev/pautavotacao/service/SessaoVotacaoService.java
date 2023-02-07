package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.SessaoVotacaoDTO;
import com.pedrodev.pautavotacao.model.entity.Pauta;
import com.pedrodev.pautavotacao.model.entity.SessaoVotacao;
import com.pedrodev.pautavotacao.repository.SessaoVotacaoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    private final PublicaResultadoVotacaoService publicaResultadoVotacaoService;
    private final ModelMapper modelMapper;
    private static Logger logger = LoggerFactory.getLogger(SessaoVotacaoService.class);

    @Value("${session.default-time}")
    private Integer defaultSessionTime;

    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaService pautaService, PublicaResultadoVotacaoService publicaResultadoVotacaoService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
        this.publicaResultadoVotacaoService = publicaResultadoVotacaoService;
        this.modelMapper = new ModelMapper();
    }

    public SessaoVotacaoDTO createAndStartSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
        validaSessaoVotacao(sessaoVotacaoDTO);
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        Pauta pauta = modelMapper.map(pautaService.findPautaById(sessaoVotacaoDTO.getPauta().getId()), Pauta.class);
        LocalDateTime now = LocalDateTime.now();

        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataAbertura(now);
        sessaoVotacao.setDataEncerramento(sessaoVotacaoDTO.getDataEncerramento());

        if(sessaoVotacao.getDataEncerramento() == null){
            sessaoVotacao.setDataEncerramento(now.plusMinutes(defaultSessionTime));
        }
        sessaoVotacaoRepository.save(sessaoVotacao);
        logger.info("New SessaoVotacao created for Pauta {}", sessaoVotacao.getPauta().getId() );

        publicaResultadoVotacaoService.agendaPublicacaoResultadoVotacao(sessaoVotacao);
        return modelMapper.map(sessaoVotacao, SessaoVotacaoDTO.class);
    }

    protected boolean hasOpenSessionByPautaId(Long pautaId) {
        LocalDateTime now = LocalDateTime.now();
        SessaoVotacao sessionVotacao = sessaoVotacaoRepository.findByPautaId(pautaId);
        boolean sessionIsNotNull = sessionVotacao != null;

        return sessionIsNotNull && now.isAfter(sessionVotacao.getDataAbertura()) && now.isBefore(sessionVotacao.getDataEncerramento());
    }

    protected boolean hasSessionByPautaId(Long pautaId) {
        return sessaoVotacaoRepository.existsByPautaId(pautaId);
    }

    private void validaSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
        if(!pautaService.isPautaExists(sessaoVotacaoDTO.getPauta().getId())){
            throw new BadRequestException("error.sessaovotacao.pauta-nao-econtrada", sessaoVotacaoDTO.getPauta().getId());
        }
        if(sessaoVotacaoRepository.existsByPautaId(sessaoVotacaoDTO.getPauta().getId())){
            throw new BadRequestException("error.sessaovotacao.ja-existe");
        }
        LocalDateTime dataEncerramento = sessaoVotacaoDTO.getDataEncerramento();
        boolean encerramentoIsNotNull = dataEncerramento != null;

        if(encerramentoIsNotNull && dataEncerramento.isBefore(LocalDateTime.now())){
            throw new BadRequestException("error.sessaovotacao.encerramento-invalido");
        }
    }
}