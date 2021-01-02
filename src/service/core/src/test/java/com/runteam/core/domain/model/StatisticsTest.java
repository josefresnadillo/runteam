package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatisticsTest {

	@Test
	@DisplayName("Test zero statistics ok")
	public void zeroStatisticsTest() {
		final Statistics statistics = Statistics.zero();
		assertEquals(statistics.getTotalMeters().longValue(), 0L);
		assertEquals(statistics.getTotalSeconds().longValue(), 0L);
		assertEquals(statistics.getElevationInMeters().longValue(), 0L);
	}

	@Test
	@DisplayName("Test add statistics ok")
	public void addStatisticsTest() {
		final Statistics one = new Statistics(5L, 5L, 5L);
		final Statistics other = new Statistics(10L, 10L, 10L);
		assertEquals(one.add(other).getTotalMeters().longValue(), 15L);
		assertEquals(one.add(other).getTotalSeconds().longValue(), 15L);
		assertEquals(one.add(other).getElevationInMeters().longValue(), 15L);
	}
}
