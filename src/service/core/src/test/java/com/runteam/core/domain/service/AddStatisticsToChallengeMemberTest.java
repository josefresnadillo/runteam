package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddStatisticsToChallengeMemberTest {

	@Test
	@DisplayName("Test challenge member add statistics ok")
	public void teamMemberActiveOkTest() {

		final ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId());
		challengeMember.setStatus(Status.ACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final AddStatisticsToChallengeMember addStatisticsToTeamMember = new AddStatisticsToChallengeMember();
		final ChallengeMember result = addStatisticsToTeamMember.add(challengeMember, statistics, OffsetDateTime.now(ZoneId.of("UTC")).plusHours(1));

		assertEquals(statistics, result.getStatistics());
	}

	@Test
	@DisplayName("Test challenge member inactive add statistics ok")
	public void teamMemberInactiveOkTest() {

		final ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId());
		challengeMember.setStatus(Status.INACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final AddStatisticsToChallengeMember addStatisticsToTeamMember = new AddStatisticsToChallengeMember();
		final ChallengeMember result = addStatisticsToTeamMember.add(challengeMember, statistics, OffsetDateTime.now(ZoneId.of("UTC")).plusHours(1));

		assertEquals(Statistics.zero(), result.getStatistics());
	}

	@Test
	@DisplayName("Test challenge member add statistics from the past ok")
	public void statisticsFromThePastActiveOkTest() {

		final ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId());
		challengeMember.setStatus(Status.ACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final AddStatisticsToChallengeMember addStatisticsToTeamMember = new AddStatisticsToChallengeMember();
		final ChallengeMember result = addStatisticsToTeamMember.add(challengeMember, statistics, OffsetDateTime.now(ZoneId.of("UTC")).minusHours(1));

		assertEquals(Statistics.zero(), result.getStatistics());
	}
}
