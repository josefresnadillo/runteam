package com.runteam.core.domain.model;

// Value Object

import java.util.Arrays;

public enum UserSubscriptionType {
	BASIC(3, 3, 3, 30, 3, 30),
	PREMIUM(10, 10, 10, 100, 10, 100),
	ADMIN(1000000, 1000000, 1000000, 1000000, 1000000, 1000000);

	// Max number of teams a user can belong to
	private final int maxTeamsUserCanBelong;

	// Max number of teams a user can create
	private final int maxTeams;

	// Max number of challenges a user can create
	private final int maxChallenges;

	// Max number of user a team created by the user can have
	private final int maxTeamMembers;

	// Max number of challenge a team created by the user can belong to
	private final int maxChallengesTeamCanBelong;

	// Max number of teams a challenge created by the user can have
	private final int maxChallengeTeams;

	UserSubscriptionType(final int maxTeamsUserCanBelong,
	                     final int maxTeams,
	                     final int maxChallenges,
	                     final int maxTeamMembers,
	                     final int maxChallengesTeamCanBelong,
	                     final int maxChallengeTeams) {
		this.maxTeamsUserCanBelong = maxTeamsUserCanBelong;
		this.maxTeams = maxTeams;
		this.maxChallenges = maxChallenges;
		this.maxTeamMembers = maxTeamMembers;
		this.maxChallengesTeamCanBelong = maxChallengesTeamCanBelong;
		this.maxChallengeTeams = maxChallengeTeams;
	}

	public int getMaxTeamsUserCanBelong() {
		return maxTeamsUserCanBelong;
	}

	public int getMaxTeams() {
		return maxTeams;
	}

	public int getMaxChallenges() {
		return maxChallenges;
	}

	public int getMaxTeamMembers() {
		return maxTeamMembers;
	}

	public int getMaxChallengesTeamCanBelong() {
		return maxChallengesTeamCanBelong;
	}

	public int getMaxChallengeTeams() {
		return maxChallengeTeams;
	}

	public static UserSubscriptionType adapt(final String type){
		return Arrays.stream(UserSubscriptionType.values())
				.filter(subscriptionType -> subscriptionType.name().equalsIgnoreCase(type))
				.findFirst()
				.orElse(UserSubscriptionType.BASIC);
	}
}
