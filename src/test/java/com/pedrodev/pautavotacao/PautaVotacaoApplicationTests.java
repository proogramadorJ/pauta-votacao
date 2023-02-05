package com.pedrodev.pautavotacao;

import com.pedrodev.pautavotacao.controller.UserController;
import com.pedrodev.pautavotacao.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PautaVotacaoApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		Assertions.assertThat(userController).isNotNull();
		Assertions.assertThat(userService).isNotNull();
	}
}
