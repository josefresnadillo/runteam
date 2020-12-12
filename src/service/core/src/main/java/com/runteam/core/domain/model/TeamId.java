package com.runteam.core.domain.model;

// Value Object

public class TeamId extends GenericId {

	public static final TeamId EMPTY = new TeamId("") {
		public boolean isEmpty() {
			return true;
		}
	};

	public TeamId(final String id) {
		super(id);
	}

	public static TeamId randomTeamId() {
		return new TeamId(generate());
	}

	public boolean isEmpty() {
		return false;
	}
}
