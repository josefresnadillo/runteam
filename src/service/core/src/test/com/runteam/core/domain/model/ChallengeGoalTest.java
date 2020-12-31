package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeGoalTest {

	@Test
	@DisplayName("Test zero challenge goal ok")
	public void zeroChallengeGoalTest() {
		final ChallengeGoal challengeGoal = ChallengeGoal.zero();
		assertEquals(challengeGoal.getActiveFrom(), OffsetDateTime.MIN);
		assertEquals(challengeGoal.getActiveTo(), OffsetDateTime.MAX);
		assertEquals(challengeGoal.getMeters(), 0);
		assertEquals(challengeGoal.getElevationInMeters(), 0);
	}
}
