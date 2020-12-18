package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamMemberTest {

	@Test
	@DisplayName("Test team member details")
	public void getDetailsTest() {
		final UserId userId = new UserId("userId");
		final OffsetDateTime activeFrom = OffsetDateTime.now();
		final OffsetDateTime activeTo = OffsetDateTime.MAX;
		final Statistics statistics = new Statistics(10L, 10L, 10L);
		final TeamMember teamMember = new TeamMember(userId,
		                                             activeFrom,
		                                             activeTo,
		                                             statistics);
		assertEquals(teamMember.getUserId(), userId);
		assertEquals(teamMember.getActiveFrom(), activeFrom);
		assertEquals(teamMember.getActiveTo(), activeTo);
		assertEquals(teamMember.getStatistics(), statistics);
	}

	@Test
	@DisplayName("Test team member is active")
	public void memberIsEmptyTest() {
		assertTrue(TeamMember.EMPTY.isEmpty());
	}

	@Test
	@DisplayName("Test team member is active")
	public void memberIsNotEmptyTest() {
		final TeamMember teamMember = new TeamMember(new UserId("teamId"),
		                                             OffsetDateTime.now(),
		                                             OffsetDateTime.MAX,
		                                             Statistics.zero());
		assertFalse(teamMember.isEmpty());
	}

	@Test
	@DisplayName("Test team member is active")
	public void memberActiveTest() {
		final TeamMember teamMember = new TeamMember(new UserId("teamId"),
		                                             OffsetDateTime.now(),
		                                             OffsetDateTime.MAX,
		                                             Statistics.zero());
		assertTrue(teamMember.isActive());
	}

	@Test
	@DisplayName("Test team member is inactive")
	public void memberInactiveTest() {
		final TeamMember teamMember = new TeamMember(new UserId("teamId"),
		                                             OffsetDateTime.now(),
		                                             OffsetDateTime.now().minusDays(1),
		                                             Statistics.zero());
		assertTrue(teamMember.isInactive());
	}

	@Test
	@DisplayName("Test team member add statistics")
	public void memberAddStatisticsTest() {
		final TeamMember teamMember = new TeamMember(new UserId("teamId"),
		                                             OffsetDateTime.now(),
		                                             OffsetDateTime.now().minusDays(1),
		                                             Statistics.zero());
		final Statistics newStatistics = teamMember.addStatistics(new Statistics(10L, 10L, 10L));
		assertEquals(newStatistics.getTotalMeters(), 10);
		assertEquals(newStatistics.getTotalSeconds(), 10);
		assertEquals(newStatistics.getElevationInMeters(), 10);
	}
}
