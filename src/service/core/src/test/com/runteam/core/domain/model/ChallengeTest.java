package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
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
		final Challenge challenge = new Challenge(new ChallengeId("challengeId"), new UserId("ownerId"));
		challenge.setDetails(challengeDetails);
		challenge.setStatus(ChallengeStatus.SUSPENDED);
		challenge.setPrivacy(Privacy.PUBLIC);
		challenge.setActivationDate(OffsetDateTime.MAX);
		challenge.setGoal(ChallengeGoal.zero());
		assertEquals(challenge.getDetails(), challengeDetails);
		assertEquals(challenge.getStatus(), ChallengeStatus.SUSPENDED);
		assertEquals(challenge.getPrivacy(), Privacy.PUBLIC);
		assertEquals(challenge.getActivationDate(), OffsetDateTime.MAX);
		assertEquals(challenge.getGoal(), ChallengeGoal.zero());
	}

	@Test
	@DisplayName("Test challenge add member")
	public void addMembersTest() {
		final Challenge team = new Challenge(new ChallengeId("challengeId"), new UserId("ownerId"));
		team.addMember(new TeamId("teamIdActive"), OffsetDateTime.now());
		assertEquals(team.getActiveMembers().size(), 1);
		assertEquals(team.getInactiveMembers().size(), 0);
	}

	@Test
	@DisplayName("Test challenge add existing member")
	public void addExistingMembersTest() {
		final TeamId teamIdActive = new TeamId("teamIdActive");
		final Challenge challenge = new Challenge(new ChallengeId("teamId"), new UserId("ownerId"));
		challenge.addMember(teamIdActive, OffsetDateTime.now());
		challenge.addMember(teamIdActive, OffsetDateTime.now());
		assertEquals(challenge.getActiveMembers().size(), 1);
		assertEquals(challenge.getInactiveMembers().size(), 0);
	}

	@Test
	@DisplayName("Test challenge add member that is inactive now")
	public void addInactiveMemberTest() {
		final TeamId teamIdInactive = new TeamId("teamIdInactive");

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"), new UserId("ownerId"));
		challenge.addMember(new TeamId("teamIdActive"), OffsetDateTime.now());
		challenge.addMember(teamIdInactive, OffsetDateTime.now());
		challenge.removeMember(teamIdInactive, OffsetDateTime.now()); // set to inactive

		challenge.addMember(teamIdInactive, OffsetDateTime.now()); // add again
		assertEquals(challenge.getActiveMembers().size(), 2);
		assertEquals(challenge.getInactiveMembers().size(), 0);
	}

	@Test
	@DisplayName("Test challenge remove member")
	public void removeMembersTest() {
		final TeamId teamIdActive = new TeamId("teamIdActive");

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"), new UserId("ownerId"));
		challenge.addMember(teamIdActive, OffsetDateTime.now());
		challenge.removeMember(teamIdActive, OffsetDateTime.now());

		assertEquals(challenge.getActiveMembers().size(), 0);
		assertEquals(challenge.getInactiveMembers().size(), 1);
	}

	@Test
	@DisplayName("Test challenge add applicant")
	public void addApplicantTest() {
		final Challenge challenge = new Challenge(new ChallengeId("id"), new UserId("ownerId"));
		challenge.addApplicant(new TeamId("id1"));

		assertEquals(challenge.getApplicants().size(), 1);
	}

	@Test
	@DisplayName("Test challenge add existing applicant")
	public void addExistingApplicantTest() {
		final TeamId teamId = new TeamId("id1");

		final Challenge challenge = new Challenge(new ChallengeId("id"), new UserId("ownerId"));
		challenge.addApplicant(teamId);
		challenge.addApplicant(teamId);

		assertEquals(challenge.getApplicants().size(), 1);
	}

	@Test
	@DisplayName("Test challenge remove existing applicant")
	public void removeExistingApplicantTest() {
		final TeamId teamId = new TeamId("id1");

		final Challenge challenge = new Challenge(new ChallengeId("id"), new UserId("ownerId"));
		challenge.addApplicant(teamId);
		challenge.removeApplicant(teamId);

		assertEquals(challenge.getApplicants().size(), 0);
	}

	@Test
	@DisplayName("Test challenge remove non existing applicant")
	public void removeNonExistingApplicantTest() {
		final Challenge challenge = new Challenge(new ChallengeId("id"), new UserId("ownerId"));
		challenge.addApplicant(new TeamId("id1"));
		challenge.removeApplicant(new TeamId("otherId"));

		assertEquals(challenge.getApplicants().size(), 1);
	}

	@Test
	@DisplayName("Test challenge add statistics")
	public void AddStatisticsTest() {
		final TeamId teamId = new TeamId("id1");

		final Challenge challenge = new Challenge(new ChallengeId("id"), new UserId("ownerId"));
		challenge.addMember(teamId, OffsetDateTime.now());
		challenge.addStatistics(teamId, new Statistics(10L, 10L, 10L));
		challenge.addStatistics(teamId, new Statistics(5L, 5L, 5L));

		assertEquals(challenge.statistics().getTotalMeters(), 15);
		assertEquals(challenge.statistics().getTotalSeconds(), 15);
		assertEquals(challenge.statistics().getElevationInMeters(), 15);
	}

	@Test
	@DisplayName("Test challenge add statistics of non existing applicant")
	public void AddStatisticsNonExistingUserTest() {
		final Challenge challenge = new Challenge(new ChallengeId("id"), new UserId("ownerId"));

		challenge.addMember(new TeamId("id1"), OffsetDateTime.now());
		challenge.addStatistics(new TeamId("nonExisting"), new Statistics(10L, 10L, 10L));

		assertEquals(challenge.statistics().getTotalMeters(), 0);
		assertEquals(challenge.statistics().getTotalSeconds(), 0);
		assertEquals(challenge.statistics().getElevationInMeters(), 0);
	}
}
