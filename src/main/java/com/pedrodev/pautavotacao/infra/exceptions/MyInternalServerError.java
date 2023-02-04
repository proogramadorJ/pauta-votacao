package com.pedrodev.pautavotacao.infra.exceptions;

public class MyInternalServerError extends RuntimeException {

	private static final long serialVersionUID = 7080950824609156425L;

	private String code;
	private Object[] params;

	public MyInternalServerError(String code, Object... params) {
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
