package com.runteam.core.domain.model;

// Value Object

public class TeamMemberId extends GenericId {

	public static final TeamMemberId EMPTY = new TeamMemberId("") {
		public boolean isEmpty() {
			return true;
		}
	};

	public TeamMemberId(final String id) {
		super(id);
	}

	public static TeamMemberId randomId() {
		return new TeamMemberId(generate());
	}

	public boolean isEmpty() {
		return false;
	}
}
