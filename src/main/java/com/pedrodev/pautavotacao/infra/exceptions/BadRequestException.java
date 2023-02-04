package com.pedrodev.pautavotacao.infra.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 7595381796596127570L;

	private String code;
	private Object[] params;

	public BadRequestException(String code, Object... params) {
		this.code = code;
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public Object[] getParams() {
		return params;
	}
}
