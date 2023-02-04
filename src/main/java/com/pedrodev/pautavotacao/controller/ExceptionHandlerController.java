package com.pedrodev.pautavotacao.controller;


import com.pedrodev.pautavotacao.infra.exceptions.EntityNotFoundException;
import com.pedrodev.pautavotacao.model.dto.SimpleMessage;
import com.pedrodev.pautavotacao.infra.exceptions.BadRequestException;
import com.pedrodev.pautavotacao.infra.exceptions.MyInternalServerError;
import com.pedrodev.pautavotacao.service.MsgResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

	private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

	private final MsgResource msgSource;

	public ExceptionHandlerController(MsgResource msgSource) {
		this.msgSource = msgSource;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public SimpleMessage handleBadRequestException(BadRequestException ex) {
		logger.error("BAD_REQUEST: {}", ex.getCode());
		return msgSource.getSimpleMessage(ex.getCode(), ex.getParams());
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public SimpleMessage handleGenericException(RuntimeException ex) {
		logger.error("INTERNAL_SERVER_ERROR: " + ex.getMessage(), ex);
		return new SimpleMessage("error", ex.getMessage());
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MyInternalServerError.class)
	public SimpleMessage handleServerErrorException(MyInternalServerError ex) {
		logger.error("INTERNAL_SERVER_ERROR: {} ", ex.getCode());
		return msgSource.getSimpleMessage(ex.getCode(), ex.getParams());
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public SimpleMessage handleNotFoundException(EntityNotFoundException ex) {
		logger.warn("NOT_FOUND: " + ex.getMessage(), ex);
		return msgSource.getSimpleMessage(ex.getCode(), ex.getParams());
	}

}
