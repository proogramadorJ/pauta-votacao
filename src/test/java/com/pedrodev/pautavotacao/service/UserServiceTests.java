package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.model.dto.UserDTO;
import com.pedrodev.pautavotacao.model.entity.User;
import com.pedrodev.pautavotacao.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    private UserDTO userDTO;

    @BeforeAll
    void init(){

        userDTO = UserDTO.builder()
                .username("username")
                .email("email@email.com")
                .build();

        user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setCreateTime(LocalDateTime.now());
    }

    @Test
    @DisplayName("Test createUser Success")
    void testCreateUser(){

        Mockito.doReturn(null).when(userRepository).findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        Mockito.doReturn(user).when(userRepository).save(any());
        UserDTO savedUserDto =  userService.createUser(userDTO);

        Assertions.assertNotNull(savedUserDto);
        Assertions.assertNotNull(savedUserDto.getCreateTime());
    }

    @Test
    @DisplayName("Test createUser fail when user already registered")
    void testCreateUserAlreadyRegistered(){

        Mockito.doReturn(user).when(userRepository).findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> userService.createUser(userDTO));

        Assertions.assertTrue(exception.getCode().equals("error.user.username-cadastrado"));

    }
}
