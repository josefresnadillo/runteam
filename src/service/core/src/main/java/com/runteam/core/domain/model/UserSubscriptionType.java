package com.runteam.core.domain.model;

// Value Object

public enum UserSubscriptionType {
	BASIC(3, 3, 30, 3),
	PREMIUM(10, 10, 100, 10);

	private final int maxTeams;
	private final int maxChallenges;
	private final int maxUserTeams;
	private final int maxTeamChallenges;

	UserSubscriptionType(final int maxTeams,
	                     final int maxChallenges,
	                     final int maxUserTeams,
	                     final int maxTeamChallenges) {
		this.maxTeams = maxTeams;
		this.maxChallenges = maxChallenges;
		this.maxUserTeams = maxUserTeams;
		this.maxTeamChallenges = maxTeamChallenges;
	}

	public int getMaxTeams() {
		return maxTeams;
	}

	public int getMaxChallenges() {
		return maxChallenges;
	}

	public int getMaxUserTeams() {
		return maxUserTeams;
	}

	public int getMaxTeamChallenges() {
		return maxTeamChallenges;
	}
}
