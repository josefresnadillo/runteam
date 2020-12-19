package com.runteam.core.domain.model;

// Value Object

public class ChallengeId extends GenericId {

	public static final ChallengeId EMPTY = new ChallengeId("") {
		public boolean isEmpty() {
			return true;
		}
	};

	public ChallengeId(final String id) {
		super(id);
	}

	public static ChallengeId randomId() {
		return new ChallengeId(generate());
	}

	public boolean isEmpty() {
		return false;
	}
}
