package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.UserDTO;
import com.pedrodev.pautavotacao.model.entity.User;
import com.pedrodev.pautavotacao.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
        modelMapper = new ModelMapper();
    }
    public UserDTO createUser(UserDTO userDTO) {
        normalize(userDTO);
        verificaSeUsuarioJaCadastrado(userDTO);
        userDTO.setCreateTime(LocalDateTime.now());

        User newUser = modelMapper.map(userDTO, User.class);
        userRepository.save(newUser);
        logger.info("User created {}", userDTO.getUsername());

        return modelMapper.map(newUser, UserDTO.class);

    }

    private void verificaSeUsuarioJaCadastrado(UserDTO userDTO) {
        User user = userRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail() );
        if(user != null){
            String newUsername = userDTO.getUsername().trim().toLowerCase();
            if(newUsername.equals(user.getUsername())){
                throw new BadRequestException("error.user.username.cadastrado", userDTO.getUsername());
            }
            throw new BadRequestException("error.user.email.cadastrado", userDTO.getEmail());
        }
    }

    private void normalize(UserDTO userDTO){
        userDTO.setUsername(userDTO.getUsername().trim().toLowerCase());
        userDTO.setEmail(userDTO.getEmail().trim().toLowerCase());
    }

}
