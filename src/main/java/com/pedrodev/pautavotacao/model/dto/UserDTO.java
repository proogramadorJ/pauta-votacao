package com.pedrodev.pautavotacao.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    @NotEmpty(message = "Username não pode ser vazio")
    @Size(min = 3, message = "Nome de usuário deve ter pelo menos 3 caractes")
    private String username;

    @NotEmpty(message = "Email não pode ser vazio")
    @Email
    private String email;
    private LocalDateTime createTime;
}
