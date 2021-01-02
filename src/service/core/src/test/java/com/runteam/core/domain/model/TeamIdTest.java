package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamIdTest {

	@Test
	@DisplayName("Test generate id")
	public void generateId() {
		final TeamId id = TeamId.randomId();
		assertFalse(id.isEmpty());
	}
}
