package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeMemberTest {

	@Test
	@DisplayName("Test challenge member details")
	public void getDetailsTest() {
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final TeamId teamId = new TeamId("teamId");
		final ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId(),
		                                                            challengeId,
		                                                            teamId);
		challengeMember.setStatus(Status.ACTIVE);

		assertEquals(challengeMember.getChallengeId(), challengeId);
		assertNotNull(challengeMember.getCreationDate());
		assertEquals(challengeMember.getTeamId(), teamId);
		assertEquals(challengeMember.getStatus(), Status.ACTIVE);
		assertEquals(challengeMember.getStatistics(), Statistics.zero());
	}

	@Test
	@DisplayName("Test challenge member is empty")
	public void memberIsEmptyTest() {
		assertTrue(ChallengeMember.EMPTY.isEmpty());
	}

	@Test
	@DisplayName("Test challenge member is not empty")
	public void memberIsNotEmptyTest() {
		final ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId());
		assertFalse(challengeMember.isEmpty());
	}

	@Test
	@DisplayName("Test challenge member add statistics")
	public void memberAddStatisticsTest() {
		final ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId());
		challengeMember.addStatistics(new Statistics(10L, 10L, 10L));
		assertEquals(challengeMember.getStatistics().getTotalMeters().longValue(), 10L);
		assertEquals(challengeMember.getStatistics().getTotalSeconds().longValue(), 10L);
		assertEquals(challengeMember.getStatistics().getElevationInMeters().longValue(), 10L);
	}
}
