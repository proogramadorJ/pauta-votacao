package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.SessaoVotacaoDTO;
import com.pedrodev.pautavotacao.model.entity.Pauta;
import com.pedrodev.pautavotacao.model.entity.SessaoVotacao;
import com.pedrodev.pautavotacao.repository.SessaoVotacaoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;
    private final ModelMapper modelMapper;
    private static Logger logger = LoggerFactory.getLogger(SessaoVotacaoService.class);

    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
        this.modelMapper = new ModelMapper();
    }

    public SessaoVotacaoDTO createAndStartSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
        validaSessaoVotacao(sessaoVotacaoDTO);
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        Pauta pauta = modelMapper.map(pautaService.findPautaById(sessaoVotacaoDTO.getPauta().getId()), Pauta.class);
        LocalDateTime now = LocalDateTime.now();

        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataCriacao(now); // TODO isso é mesmo necessario?
        sessaoVotacao.setDataAbertura(now);
        sessaoVotacao.setDataEncerramento(sessaoVotacaoDTO.getDataEncerramento());
        if(sessaoVotacao.getDataEncerramento() == null){
            sessaoVotacao.setDataEncerramento(now.plusMinutes(1));
        }
        sessaoVotacaoRepository.save(sessaoVotacao);
        // TODO criar agendamento aqui
        return modelMapper.map(sessaoVotacao, SessaoVotacaoDTO.class);
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