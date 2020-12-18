package com.runteam.core.domain.model;

public class DomainException extends RuntimeException {

	private final DomainExceptionCode code;

	public DomainException(final DomainExceptionCode code) {
		super(code.getMessage());
		this.code = code;
	}

	public DomainExceptionCode getCode() {
		return code;
	}
}
