package com.runteam.core.domain.model;

// Value Object

public enum UserSubscriptionType {
	BASIC(3, 3, 30, 3, 30),
	PREMIUM(10, 10, 100, 10, 100);

	// Max number of teams a user can create
	private final int maxTeams;

	// Max number of challenges a user can create
	private final int maxChallenges;

	// Max number of user a team can have
	private final int maxUserTeams;

	// Max number of challenge a team can be
	private final int maxTeamChallenges;

	// Max number of teams a challenge can have
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
