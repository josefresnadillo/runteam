package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeGoalTest {

	@Test
	@DisplayName("Test zero challenge goal ok")
	public void zeroChallengeGoalTest() {
		final ChallengeGoal challengeGoal = ChallengeGoal.zero();
		assertEquals(challengeGoal.getActiveFrom(), ChallengeGoal.DEFAULT_VALID_FROM);
		assertEquals(challengeGoal.getActiveTo(), ChallengeGoal.DEFAULT_VALID_TO);
		assertEquals(challengeGoal.getMeters(), 0);
		assertEquals(challengeGoal.getElevationInMeters(), 0);
	}
}
