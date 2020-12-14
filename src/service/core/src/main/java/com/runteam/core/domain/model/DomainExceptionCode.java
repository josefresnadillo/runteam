package com.runteam.core.domain.model;

public enum DomainExceptionCode {
	TOO_MANY_TEAMS("Too many teams for subscription type"),
	TOO_MANY_CHALLENGES("Too many challenges for subscription type"),
	TOO_MANY_USERS_IN_TEAMS("Too many users in teams for subscription type"),
	TEAM_IS_PRIVATE("Team is private"),
	CHALLENGE_IS_PRIVATE("Challenge is private"),
	TEAM_IN_TOO_MANY_CHALLENGES("Team in too many challenges for subscription type"),
	CHALLENGE_HAS_TOO_MANY_TEAMS("Challenge has too many teams for subscription type");

	private final String msg;

	DomainExceptionCode(final String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
