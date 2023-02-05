package com.pedrodev.pautavotacao.repository;

import com.pedrodev.pautavotacao.model.entity.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    boolean existsByPautaId(Long pautaId);

    SessaoVotacao findByPautaId(Long pautaId);
}
