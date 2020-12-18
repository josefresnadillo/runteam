package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserIdTest {

	@Test
	@DisplayName("Test generate id")
	public void generateId() {
		final UserId id = UserId.randomUserId();
		assertFalse(id.isEmpty());
	}
}
