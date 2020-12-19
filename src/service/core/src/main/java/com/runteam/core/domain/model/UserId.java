package com.runteam.core.domain.model;

// Value Object

public class UserId extends GenericId {

	public static final UserId EMPTY = new UserId("") {
		public boolean isEmpty() {
			return true;
		}
	};

	public UserId(final String id) {
		super(id);
	}

	public static UserId randomId() {
		return new UserId(generate());
	}

	public boolean isEmpty() {
		return false;
	}
}
