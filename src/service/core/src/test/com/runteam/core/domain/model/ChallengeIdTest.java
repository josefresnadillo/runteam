package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeIdTest {

	@Test
	@DisplayName("Test generate id")
	public void generateId() {
		final ChallengeId id = ChallengeId.randomChallengeId();
		assertFalse(id.isEmpty());
	}
}
