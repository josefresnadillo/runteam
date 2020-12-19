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
	private final int maxTeamsUserBelongs;

	// Max number of challenge a team can belong to
	private final int maxChallengesTeamBelongs;

	// Max number of teams a challenge can have
	private final int maxChallengeTeams;

	UserSubscriptionType(final int maxTeams,
	                     final int maxChallenges,
	                     final int maxTeamsUserBelongs,
	                     final int maxChallengesTeamBelongs,
	                     final int maxChallengeTeams) {
		this.maxTeams = maxTeams;
		this.maxChallenges = maxChallenges;
		this.maxTeamsUserBelongs = maxTeamsUserBelongs;
		this.maxChallengesTeamBelongs = maxChallengesTeamBelongs;
		this.maxChallengeTeams = maxChallengeTeams;
	}

	public int getMaxTeams() {
		return maxTeams;
	}

	public int getMaxChallenges() {
		return maxChallenges;
	}

	public int getMaxTeamsUserBelongs() {
		return maxTeamsUserBelongs;
	}

	public int getMaxChallengesTeamBelongs() {
		return maxChallengesTeamBelongs;
	}

	public int getMaxChallengeTeams() {
		return maxChallengeTeams;
	}
}
