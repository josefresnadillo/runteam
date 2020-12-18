package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeMemberTest {

	@Test
	@DisplayName("Test challenge member details")
	public void getDetailsTest() {
		final TeamId teamId = new TeamId("teamId");
		final OffsetDateTime activeFrom = OffsetDateTime.now();
		final OffsetDateTime activeTo = OffsetDateTime.MAX;
		final Statistics statistics = new Statistics(10L, 10L, 10L);
		final ChallengeMember challengeMember = new ChallengeMember(teamId,
		                                                            activeFrom,
		                                                            activeTo,
		                                                            statistics);
		assertEquals(challengeMember.getTeamId(), teamId);
		assertEquals(challengeMember.getActiveFrom(), activeFrom);
		assertEquals(challengeMember.getActiveTo(), activeTo);
		assertEquals(challengeMember.getStatistics(), statistics);
	}

	@Test
	@DisplayName("Test challenge member is active")
	public void memberIsEmptyTest() {
		assertTrue(ChallengeMember.EMPTY.isEmpty());
	}

	@Test
	@DisplayName("Test challenge member is active")
	public void memberIsNotEmptyTest() {
		final ChallengeMember challengeMember = new ChallengeMember(new TeamId("teamId"),
		                                                            OffsetDateTime.now(),
		                                                            OffsetDateTime.MAX,
		                                                            Statistics.zero());
		assertFalse(challengeMember.isEmpty());
	}

	@Test
	@DisplayName("Test challenge member is active")
	public void memberActiveTest() {
		final ChallengeMember challengeMember = new ChallengeMember(new TeamId("teamId"),
		                                                            OffsetDateTime.now(),
		                                                            OffsetDateTime.MAX,
		                                                            Statistics.zero());
		assertTrue(challengeMember.isActive());
	}

	@Test
	@DisplayName("Test challenge member is inactive")
	public void memberInactiveTest() {
		final ChallengeMember challengeMember = new ChallengeMember(new TeamId("teamId"),
		                                                            OffsetDateTime.now(),
		                                                            OffsetDateTime.now().minusDays(1),
		                                                            Statistics.zero());
		assertTrue(challengeMember.isInactive());
	}

	@Test
	@DisplayName("Test challenge member add statistics")
	public void memberAddStatisticsTest() {
		final ChallengeMember challengeMember = new ChallengeMember(new TeamId("teamId"),
		                                                            OffsetDateTime.now(),
		                                                            OffsetDateTime.now().minusDays(1),
		                                                            Statistics.zero());
		final Statistics newStatistics = challengeMember.addStatistics(new Statistics(10L, 10L, 10L));
		assertEquals(newStatistics.getTotalMeters(), 10);
		assertEquals(newStatistics.getTotalSeconds(), 10);
		assertEquals(newStatistics.getElevationInMeters(), 10);
	}
}
