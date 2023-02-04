package com.pedrodev.pautavotacao.service;

import com.pedrodev.pautavotacao.model.dto.SimpleMessage;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MsgResource {

	private final MessageSource messageSource;

	private Locale locale = new Locale("pt_BR");

	public MsgResource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Pega a mensagem correspondente ao codigo no arquivo
	 * resources/message.properties
	 *
	 * @param code odigo no resources/message.properties
	 * @param args array de argumentos
	 * @return string mensagem do arquivo message.properties
	 */
	public String getMsg(String code, Object... args) {
		return messageSource.getMessage(code, args, locale);
	}

	/**
	 * Cria uma SimpleMessage com o codigo do resources/message.properties
	 *
	 * @param code codigo no resources/message.properties
	 * @param args array de argumentos
	 * @return SimpleMessage
	 */
	public SimpleMessage getSimpleMessage(String code, Object... args) {
		return new SimpleMessage(code, getMsg(code, args));
	}

}
