package com.pedrodev.pautavotacao.controller;

import com.pedrodev.pautavotacao.model.dto.UserDTO;
import com.pedrodev.pautavotacao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Validated @RequestBody  UserDTO userDTO){
        logger.debug("Request to create a new User " + userDTO.getUsername());
        return userService.createUser(userDTO);
    }
}
