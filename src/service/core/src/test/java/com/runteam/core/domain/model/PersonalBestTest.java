package com.runteam.core.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalBestTest {

	@Test
	@DisplayName("Test factory 10k ok")
	public void factory10kTest() {
		final PersonalBest best = PersonalBest.create(10000L, 3000L); // 5 min/km
		assertEquals((long)best.getBest1kInSeconds(), 300L);
		assertEquals((long)best.getBest5kInSeconds(), 1500L);
		assertEquals((long)best.getBest10kInSeconds(), 3000L);
		assertEquals((long)best.getBestHalfMarathonInSeconds(), 0L);
		assertEquals((long)best.getBestMarathonInSeconds(), 0L);
	}

	@Test
	@DisplayName("Test factory 42k ok")
	public void factoryMarathonkTest() {
		final PersonalBest best = PersonalBest.create(42195L, 12659L); // 5 min/km
		assertEquals((long)best.getBest1kInSeconds(), 300L);
		assertEquals((long)best.getBest5kInSeconds(), 1500L);
		assertEquals((long)best.getBest10kInSeconds(), 3000L);
		assertEquals((long)best.getBestHalfMarathonInSeconds(), 6329L);
		assertEquals((long)best.getBestMarathonInSeconds(), 12659L);
	}

	@Test
	@DisplayName("Test mix ok")
	public void mixTest() {
		final PersonalBest best1 = new PersonalBest(300L,
				1500L,
				3000L,
				6329L,
				12659L);

		final PersonalBest best2 = new PersonalBest(290L,
				1450L,
				3005L,
				6400L,
				12700L);

		final PersonalBest best = PersonalBest.mix(best1, best2);

		assertEquals((long)best.getBest1kInSeconds(), 290L);
		assertEquals((long)best.getBest5kInSeconds(), 1450L);
		assertEquals((long)best.getBest10kInSeconds(), 3000L);
		assertEquals((long)best.getBestHalfMarathonInSeconds(), 6329L);
		assertEquals((long)best.getBestMarathonInSeconds(), 12659L);
	}
}
