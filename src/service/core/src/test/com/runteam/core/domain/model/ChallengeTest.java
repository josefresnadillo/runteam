package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeTest {

	@Test
	@DisplayName("Test challenge add details, status, activation date, goal and privacy")
	public void addDetailsStatusPrivacyTest() {
		final ChallengeDetails challengeDetails = ChallengeDetails.builder()
		                                                          .name("team1")
		                                                          .displayName("Best Team")
		                                                          .imageUrl("http://google.es")
		                                                          .tags(List.of("tag1", "tag2"))
		                                                          .build();

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("ownerId"),
		                                          0);

		challenge.setDetails(challengeDetails);
		challenge.setStatus(Status.SUSPENDED);
		challenge.setPrivacy(Privacy.PUBLIC);
		challenge.setGoal(ChallengeGoal.zero());

		assertNotNull(challenge.getCreationDate());
		assertEquals(challenge.getNumberOfMembers(), 0);
		assertEquals(challenge.getDetails(), challengeDetails);
		assertEquals(challenge.getStatus(), Status.SUSPENDED);
		assertEquals(challenge.getPrivacy(), Privacy.PUBLIC);
		assertNotNull(challenge.getCreationDate());
		assertEquals(challenge.getGoal(), ChallengeGoal.zero());
	}

	@Test
	@DisplayName("Test challenge add member")
	public void addMemberTest() {
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId,
		                                          new UserId("ownerId"),
		                                          0);
		final TeamId teamId = new TeamId("teamIdActive");
		final ChallengeMember challengeMember = challenge.addMember(teamId, Status.ACTIVE);

		assertEquals(challengeMember.getChallengeId(), challengeId);
		assertEquals(challengeMember.getTeamId(), teamId);
		assertEquals(challengeMember.getStatus(), Status.ACTIVE);
		assertEquals(challengeMember.getStatistics(), Statistics.zero());
	}

	@Test
	@DisplayName("Test challenge add statistics")
	public void AddStatisticsTest() {
		final Challenge challenge = new Challenge(new ChallengeId("id"),
		                                          new UserId("ownerId"),
		                                          0);
		challenge.addStatistics(new Statistics(10L, 10L, 10L));
		challenge.addStatistics(new Statistics(5L, 5L, 5L));

		assertEquals(challenge.getStatistics().getTotalMeters(), 15);
		assertEquals(challenge.getStatistics().getTotalSeconds(), 15);
		assertEquals(challenge.getStatistics().getElevationInMeters(), 15);
	}

	@Test
	@DisplayName("Test challenge is active or throw - is active ok")
	public void activeOrThrowOkIsActive() {
		final Challenge challenge = new Challenge(new ChallengeId("id"),
		                                          new UserId("ownerId"),
		                                          0);
		challenge.setStatus(Status.ACTIVE);
		assertDoesNotThrow(challenge::checkIsActiveOrThrow);
	}

	@Test
	@DisplayName("Test challenge is active or throw no ok")
	public void activeOrThrowOkIsInactive() {
		final Challenge challenge = new Challenge(new ChallengeId("id"),
		                                          new UserId("ownerId"),
		                                          0);
		DomainException exception = assertThrows(DomainException.class, challenge::checkIsActiveOrThrow);
		assertEquals(exception.getCode(), DomainExceptionCode.CHALLENGE_IS_NOT_ACTIVE);
	}
}
