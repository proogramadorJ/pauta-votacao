package com.pedrodev.pautavotacao.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USER_SYSTEM")
public class User {
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "CREATE_TIME", nullable = false)
    private LocalDateTime createTime;

}
