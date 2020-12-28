package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.OffsetDateTime;

public class TeamTest {

	@Test
	@DisplayName("Test team add details, status, activation date and privacy")
	public void addDetailsStatusPrivacyTest() {
		final TeamDetails teamDetails = TeamDetails.builder()
		                                           .name("@team1")
		                                           .displayName("Best Team")
		                                           .city("Madrid")
		                                           .countryCode("es")
		                                           .imageUrl("https://google.com")
		                                           .build();
		final Team team = new Team(new TeamId("teamId"), new UserId("ownerId"));
		team.setDetails(teamDetails);
		team.setStatus(TeamStatus.ACTIVE);
		team.setPrivacy(Privacy.PUBLIC);
		team.setActivationDate(OffsetDateTime.MAX);
		assertEquals(team.getDetails(), teamDetails);
		assertEquals(team.getStatus(), TeamStatus.ACTIVE);
		assertEquals(team.getPrivacy(), Privacy.PUBLIC);
		assertEquals(team.getActivationDate(), OffsetDateTime.MAX);
	}

	@Test
	@DisplayName("Test team add member")
	public void addMembersTest() {
		final Team team = new Team(new TeamId("teamId"), new UserId("ownerId"));
		team.addActiveMember(new UserId("userIdActive"), OffsetDateTime.now());
		assertEquals(team.getActiveMembers().size(), 1);
	}

	@Test
	@DisplayName("Test team add existing member")
	public void addExistingMembersTest() {
		final UserId userIdActive = new UserId("userIdActive");
		final Team team = new Team(new TeamId("teamId"), new UserId("ownerId"));
		team.addActiveMember(userIdActive, OffsetDateTime.now());
		team.addActiveMember(userIdActive, OffsetDateTime.now());
		assertEquals(team.getActiveMembers().size(), 1);
		assertEquals(team.getInactiveMembers().size(), 0);
	}

	@Test
	@DisplayName("Test team add member that is inactive now")
	public void addInactiveMemberTest() {
		final UserId userIdInactive = new UserId("userIdInactive");

		final Team team = new Team(new TeamId("teamId"), new UserId("ownerId"));
		team.addActiveMember(new UserId("userIdActive"), OffsetDateTime.now());

		team.addActiveMember(userIdInactive, OffsetDateTime.now()); // add
		team.removeMember(userIdInactive); // set to inactive
		team.addActiveMember(userIdInactive, OffsetDateTime.now()); // add again

		assertEquals(team.getActiveMembers().size(), 2);
		assertEquals(team.getInactiveMembers().size(), 0);
	}

	@Test
	@DisplayName("Test team add pending member")
	public void addPendingMemberTest() {
		final UserId userIdInactive = new UserId("userIdInactive");

		final Team team = new Team(new TeamId("teamId"), new UserId("ownerId"));

		team.addPendingMember(userIdInactive, OffsetDateTime.now()); // add

		assertEquals(team.getActiveMembers().size(), 0);
		assertEquals(team.getPendingMembers().size(), 1);
		assertEquals(team.getInactiveMembers().size(), 0);
	}

	@Test
	@DisplayName("Test team remove member")
	public void removeMembersTest() {
		final UserId userIdActive = new UserId("userIdActive");
		final Team team = new Team(new TeamId("teamId"), new UserId("ownerId"));

		team.addActiveMember(userIdActive, OffsetDateTime.now());
		team.removeMember(userIdActive);

		assertEquals(team.getActiveMembers().size(), 0);
		assertEquals(team.getInactiveMembers().size(), 1);
	}

	@Test
	@DisplayName("Test team add statistics")
	public void AddStatisticsTest() {
		final UserId userId = new UserId("id1");
		final Team team = new Team(new TeamId("id"), new UserId("ownerId"));
		team.addActiveMember(userId, OffsetDateTime.now());
		team.addStatistics(userId, new Statistics(10L, 10L, 10L));
		team.addStatistics(userId, new Statistics(5L, 5L, 5L));
		assertEquals(team.statistics().getTotalMeters(), 15);
		assertEquals(team.statistics().getTotalSeconds(), 15);
		assertEquals(team.statistics().getElevationInMeters(), 15);
	}

	@Test
	@DisplayName("Test team add statistics of non existing applicant")
	public void AddStatisticsNonExistingUserTest() {
		final Team team = new Team(new TeamId("id"), new UserId("ownerId"));
		team.addActiveMember(new UserId("id1"), OffsetDateTime.now());
		team.addStatistics(new UserId("nonExisting"), new Statistics(10L, 10L, 10L));
		assertEquals(team.statistics().getTotalMeters(), 0);
		assertEquals(team.statistics().getTotalSeconds(), 0);
		assertEquals(team.statistics().getElevationInMeters(), 0);
	}
}
