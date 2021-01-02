package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamMemberTest {

	@Test
	@DisplayName("Test team member details")
	public void getDetailsTest() {
		final UserId userId = new UserId("userId");
		final Status status = Status.ACTIVE;
		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId(),
		                                             new TeamId("teamId"),
		                                             userId);
		teamMember.setStatus(status);
		assertEquals(teamMember.getUserId(), userId);
		assertNotNull(teamMember.getCreationDate());
		assertEquals(teamMember.getStatus(), status);
		assertEquals(teamMember.getStatistics(), Statistics.zero());
	}

	@Test
	@DisplayName("Test team member is empty")
	public void memberIsEmptyTest() {
		assertTrue(TeamMember.EMPTY.isEmpty());
	}

	@Test
	@DisplayName("Test team member is not empty")
	public void memberIsNotEmptyTest() {
		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId(),
		                                             new TeamId("teamId"),
		                                             new UserId("userId"));
		assertFalse(teamMember.isEmpty());
	}

	@Test
	@DisplayName("Test team member is active")
	public void memberActiveTest() {
		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId(),
		                                             new TeamId("teamId"),
		                                             new UserId("userId"));
		teamMember.setStatus(Status.ACTIVE);
		assertTrue(teamMember.isActive());
	}

	@Test
	@DisplayName("Test team member is inactive")
	public void memberInactiveTest() {
		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId(),
		                                             new TeamId("teamId"),
		                                             new UserId("userId"));
		assertSame(teamMember.getStatus(), Status.INACTIVE);
	}

	@Test
	@DisplayName("Test team member add statistics")
	public void memberAddStatisticsTest() {
		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId(),
		                                             new TeamId("teamId"),
		                                             new UserId("userId"));
		teamMember.addStatistics(new Statistics(10L, 10L, 10L));
		assertEquals(teamMember.getStatistics().getTotalMeters().longValue(), 10L);
		assertEquals(teamMember.getStatistics().getTotalSeconds().longValue(), 10L);
		assertEquals(teamMember.getStatistics().getElevationInMeters().longValue(), 10L);
	}
}
