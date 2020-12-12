package com.runteam.core.domain.model;

// Value Object

public enum UserSubscriptionType {
	BASIC(3, 3, 3),
	PREMIUM(100, 100, 3);

	private final int maxTeams;
	private final int maxChallenges;
	private final int maxTeamChallenges;

	UserSubscriptionType(final int maxTeams,
	                     final int maxChallenges,
	                     final int maxTeamChallenges) {
		this.maxTeams = maxTeams;
		this.maxChallenges = maxChallenges;
		this.maxTeamChallenges = maxTeamChallenges;
	}

	public int getMaxTeams() {
		return maxTeams;
	}

	public int getMaxChallenges() {
		return maxChallenges;
	}

	public int getMaxTeamChallenges() {
		return maxTeamChallenges;
	}
}
