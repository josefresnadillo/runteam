package com.runteam.core.domain.model;

import java.util.UUID;

// Value Object

public class GenericId {

	private final String id;

	public GenericId(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	protected static String generate() {
		return UUID.randomUUID().toString();
	}
}
