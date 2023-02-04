package com.pedrodev.pautavotacao.infra.exceptions;

public class MyEntityNotFound extends RuntimeException {

	private static final long serialVersionUID = -6943107137497013079L;

	private String code;
	private Object[] params;

	public MyEntityNotFound(String code, Object... params) {
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
