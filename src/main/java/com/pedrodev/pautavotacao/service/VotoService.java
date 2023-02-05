package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.VotoDTO;
import com.pedrodev.pautavotacao.model.entity.Pauta;
import com.pedrodev.pautavotacao.model.entity.User;
import com.pedrodev.pautavotacao.model.entity.Voto;
import com.pedrodev.pautavotacao.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService {

    private final UserService userService;

    private final PautaService pautaService;

    private final SessaoVotacaoService sessaoVotacaoService;

    private final VotoRepository votoRepository;

    private static Logger logger = LoggerFactory.getLogger(VotoService.class);

    public VotoService(UserService userService, PautaService pautaService, SessaoVotacaoService sessaoVotacaoService, VotoRepository votoRepository) {
        this.userService = userService;
        this.pautaService = pautaService;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.votoRepository = votoRepository;
    }

    public void createVoto(VotoDTO votoDTO) {
        validaVoto(votoDTO);
        Pauta pauta = new Pauta();
        pauta.setId(votoDTO.getPautaId());
        User user = new User();
        user.setId(votoDTO.getUserId());

        Voto voto = Voto.builder()
                .user(user)
                .pauta(pauta)
                .tipoVoto(votoDTO.getTipoVoto())
                .dataCriacao(LocalDateTime.now())
                .build();

        votoRepository.save(voto);
        logger.info("New vote computed for Pauta {} ", votoDTO.getPautaId());
    }

    private void validaVoto(VotoDTO votoDTO) {
        if(!pautaService.isPautaExists(votoDTO.getPautaId())){
            throw new BadRequestException("error.voto.pauta-nao-econtrada", votoDTO.getPautaId());
        }
        if(!sessaoVotacaoService.hasOpenSessionByPautaId(votoDTO.getPautaId())){
            throw new BadRequestException("error.voto.sessao-nao-econtrada", votoDTO.getPautaId());
        }
        if(votoRepository.existsByUserId(votoDTO.getUserId())){
            throw new BadRequestException("error.voto.usuario-ja-votou", votoDTO.getPautaId());
        }
    }
}