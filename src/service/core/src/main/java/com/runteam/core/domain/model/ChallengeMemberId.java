package com.runteam.core.domain.model;

// Value Object

public class ChallengeMemberId extends GenericId {

	public static final ChallengeMemberId EMPTY = new ChallengeMemberId("") {
		public boolean isEmpty() {
			return true;
		}
	};

	public ChallengeMemberId(final String id) {
		super(id);
	}

	public static ChallengeMemberId randomId() {
		return new ChallengeMemberId(generate());
	}

	public boolean isEmpty() {
		return false;
	}
}
