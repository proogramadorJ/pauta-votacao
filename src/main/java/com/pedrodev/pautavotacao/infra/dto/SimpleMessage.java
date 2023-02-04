package com.pedrodev.pautavotacao.infra.dto;

public class SimpleMessage {

	private String codigo;

	private String message;

	public SimpleMessage(String codigo, String message) {
		this.codigo = codigo;
		this.message = message;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMessage() {
		return message;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}