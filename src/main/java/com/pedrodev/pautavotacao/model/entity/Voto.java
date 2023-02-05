package com.pedrodev.pautavotacao.model.entity;

import com.pedrodev.pautavotacao.model.enumeration.TipoVoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "VOTO")
public class Voto {
    @Id
    @SequenceGenerator(name = "voto_id_seq", sequenceName = "voto_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_id_seq")
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PAUTA_ID", nullable = false)
    private Pauta pauta;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_VOTO", nullable = false)
    private TipoVoto tipoVoto;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;
    
}
