package com.pedrodev.pautavotacao.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "PAUTA")
public class Pauta {

    @Id
    @SequenceGenerator(name = "pauta_id_seq", sequenceName = "pauta_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_id_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos;

    @OneToOne(mappedBy = "pauta")
    private SessaoVotacao sessaoVotacao;

}
