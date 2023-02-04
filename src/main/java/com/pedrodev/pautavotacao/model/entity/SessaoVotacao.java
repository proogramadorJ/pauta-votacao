package com.pedrodev.pautavotacao.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SESSAO_VOTACAO")
public class SessaoVotacao {
    @Id
    @SequenceGenerator(name = "sessao_votacao_id_seq", sequenceName = "sessao_votacao_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_votacao_id_seq")
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "PAUTA_ID", nullable = false)
    private Pauta pauta;

    @Column(name = "DATA_ABERTURA", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "DATA_ENCERRAMENTO", nullable = false)
    private  LocalDateTime dataEncerramento;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

}
