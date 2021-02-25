package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AddStatisticsToTeamMemberTest {

	@Test
	@DisplayName("Test team member add statistics ok")
	public void teamMemberActiveOkTest() {

		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId());
		teamMember.setStatus(Status.ACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final DomainEvents domainEvents = mock(DomainEvents.class);

		final AddStatisticsToTeamMember addStatisticsToTeamMember = new AddStatisticsToTeamMember(domainEvents);
		final TeamMember result = addStatisticsToTeamMember.add(teamMember, statistics, OffsetDateTime.now(ZoneId.of("UTC")).plusHours(1));

		assertEquals(statistics, result.getStatistics());
	}

	@Test
	@DisplayName("Test team member add statistics inactive no add ok")
	public void teamMemberInactiveOkTest() {

		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId());
		teamMember.setStatus(Status.INACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final DomainEvents domainEvents = mock(DomainEvents.class);

		final AddStatisticsToTeamMember addStatisticsToTeamMember = new AddStatisticsToTeamMember(domainEvents);
		final TeamMember result = addStatisticsToTeamMember.add(teamMember, statistics, OffsetDateTime.now(ZoneId.of("UTC")).plusHours(1));

		assertEquals(Statistics.zero(), result.getStatistics());
	}

	@Test
	@DisplayName("Test team member add statistics from the past no add ok")
	public void statisticsFromThePastOkTest() {

		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId());
		teamMember.setStatus(Status.ACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final DomainEvents domainEvents = mock(DomainEvents.class);

		final AddStatisticsToTeamMember addStatisticsToTeamMember = new AddStatisticsToTeamMember(domainEvents);
		final TeamMember result = addStatisticsToTeamMember.add(teamMember, statistics, OffsetDateTime.now(ZoneId.of("UTC")).minusHours(1));

		assertEquals(Statistics.zero(), result.getStatistics());
	}
}
