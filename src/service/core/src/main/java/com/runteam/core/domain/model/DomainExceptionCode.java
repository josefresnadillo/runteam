package com.runteam.core.domain.model;

public enum DomainExceptionCode {
	COUNTRY_NOT_VALID("Country is not valid"),
	LANGUAGE_NOT_VALID("Language is not valid"),
	URL_NOT_VALID("URL is not valid"),
	EMAIL_NOT_VALID("Eamil is not valid"),

	USER_IS_NOT_ACTIVE("User is not active"),
	USER_CREATED_TOO_MANY_TEAMS("Too many teams for subscription type"),
	USER_CREATED_TOO_MANY_CHALLENGES("Too many challenges for subscription type"),
	USER_ALREADY_IN_TEAM("User is already in team"),
	USER_IN_TOO_MANY_TEAMS("User belongs to too many teams for subscription type"),

	TEAM_IS_NOT_ACTIVE("Team is not active"),
	TEAM_HAS_TOO_MANY_USERS("Too many users in team for subscription type"),
	TEAM_IN_TOO_MANY_CHALLENGES("Team in too many challenges for subscription type"),
	TEAM_ALREADY_IN_CHALLENGE("Team is already in challenge"),
	TEAM_OWNER_IS_NECESSARY("Only team owner can perform this action"),

	CHALLENGE_IS_NOT_ACTIVE("Challenge is not active"),
	CHALLENGE_HAS_TOO_MANY_TEAMS("Challenge has too many teams for subscription type");

	private final String msg;

	DomainExceptionCode(final String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
