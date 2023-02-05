package com.pedrodev.pautavotacao.repository;

import com.pedrodev.pautavotacao.model.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByUserId(Long userId);
}
