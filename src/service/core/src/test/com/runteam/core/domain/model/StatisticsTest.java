package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatisticsTest {

	@Test
	@DisplayName("Test zero statistics ok")
	public void zeroStatisticsTest() {
		final Statistics statistics = Statistics.zero();
		assertEquals(statistics.getTotalMeters(), 0);
		assertEquals(statistics.getTotalSeconds(), 0);
		assertEquals(statistics.getElevationInMeters(), 0);
	}

	@Test
	@DisplayName("Test add statistics ok")
	public void addStatisticsTest() {
		final Statistics one = new Statistics(5L, 5L, 5L);
		final Statistics other = new Statistics(10L, 10L, 10L);
		assertEquals(one.add(other).getTotalMeters(), 15);
		assertEquals(one.add(other).getTotalSeconds(), 15);
		assertEquals(one.add(other).getElevationInMeters(), 15);
	}
}
