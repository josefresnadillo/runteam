package com.runteam.core.domain.model;

// Value Object

public enum UserSubscriptionType {
	BASIC(3, 3, 30, 3, 30),
	PREMIUM(10, 10, 100, 10, 100);

	private final int maxTeams;
	private final int maxChallenges;
	private final int maxUserTeams;
	private final int maxTeamChallenges;
	private final int maxChallengeTeams;

	UserSubscriptionType(final int maxTeams,
	                     final int maxChallenges,
	                     final int maxUserTeams,
	                     final int maxTeamChallenges,
	                     final int maxChallengeTeams) {
		this.maxTeams = maxTeams;
		this.maxChallenges = maxChallenges;
		this.maxUserTeams = maxUserTeams;
		this.maxTeamChallenges = maxTeamChallenges;
		this.maxChallengeTeams = maxChallengeTeams;
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

	public int getMaxChallengeTeams() {
		return maxChallengeTeams;
	}
}
