package com.pedrodev.pautavotacao.repository;

import com.pedrodev.pautavotacao.model.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByUserId(Long userId);

    List<Voto> findAllByPautaId(Long pautaId);
}
