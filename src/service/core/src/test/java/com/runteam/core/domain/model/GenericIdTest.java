package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GenericIdTest {

	@Test
	@DisplayName("Test generate id")
	public void generateIdTest() {
		final String id = GenericId.generate();
		assertFalse(id.isEmpty());
	}
}
