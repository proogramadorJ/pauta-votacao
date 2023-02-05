package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.EntityNotFoundException;
import com.pedrodev.pautavotacao.model.dto.PautaDTO;
import com.pedrodev.pautavotacao.model.entity.Pauta;
import com.pedrodev.pautavotacao.repository.PautaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final ModelMapper modelMapper;
    private static Logger logger = LoggerFactory.getLogger(PautaService.class);

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
        modelMapper = new ModelMapper();
    }

    public PautaDTO createPauta(PautaDTO pautaDTO) {
        Pauta newPauta = modelMapper.map(pautaDTO, Pauta.class);
        newPauta.setDataCriacao(LocalDateTime.now());
        pautaRepository.save(newPauta);
        logger.info("Pauta created {}", newPauta.getTitulo());

        return modelMapper.map(newPauta, PautaDTO.class);
    }

    public PautaDTO updatePauta(Long id, PautaDTO pautaDTO) {
        Pauta pauta = modelMapper.map(findPautaById(id), Pauta.class) ;
        pauta.setTitulo(pautaDTO.getTitulo());
        pauta.setDescricao(pautaDTO.getDescricao());
        pautaRepository.save(pauta);
        logger.info("Pauta updated {}", pauta.getTitulo());

        return modelMapper.map(pauta, PautaDTO.class);
    }

    public PautaDTO findPautaById(Long id) {
        Optional <Pauta> pauta = pautaRepository.findById(id);
        if(pauta.isPresent()){
            return modelMapper.map(pauta.get(), PautaDTO.class);
        }
        throw new EntityNotFoundException("error.pauta.nao-econtrada", id);
    }

    public void deletePauta(Long id) {
        PautaDTO pauta = findPautaById(id);
        logger.info("Pauta {} deleted", id);
        pautaRepository.delete(modelMapper.map(pauta, Pauta.class));
    }

    public List<PautaDTO> findAll() {
        List<PautaDTO> pautasDto = pautaRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, PautaDTO.class))
                .collect(Collectors.toList());
        return pautasDto;
    }

    protected boolean isPautaExists(Long id){
        return pautaRepository.existsById(id);
    }
}