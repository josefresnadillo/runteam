package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;

public class CreateChallenge {

	public CreateChallenge() {
	}

	public Challenge active(final User managerUser,
	                        final ChallengeDetails details,
	                        final ChallengeGoal goal) {

		// Check manager user is active
		managerUser.checkIsActiveOrThrow();

		// Check the number of challenges created by the user
		managerUser.checkNumberOfChallengesCreatedOrThrow();

		// Create challenge ACTIVE
		return createChallenge(managerUser.getId(),
		                       details,
		                       goal);
	}

	private Challenge createChallenge(final UserId userId,
	                                  final ChallengeDetails details,
	                                  final ChallengeGoal goal) {
		final Challenge challenge = new Challenge(ChallengeId.randomId(),
		                                          userId,
		                                          0);
		challenge.setDetails(details);
		challenge.setGoal(goal);
		challenge.setStatus(Status.ACTIVE);
		return challenge;
	}
}
