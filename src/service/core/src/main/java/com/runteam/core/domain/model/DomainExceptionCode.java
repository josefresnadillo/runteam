package com.runteam.core.domain.model;

public enum DomainExceptionCode {
	COUNTRY_NOT_VALID("Country is not valid"),
	LANGUAGE_NOT_VALID("Language is not valid"),
	URL_NOT_VALID("URL is not valid"),
	EMAIL_NOT_VALID("Eamil is not valid"),
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

	public String getMessage() {
		return msg;
	}
}
