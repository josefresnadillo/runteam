package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

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
		final Team team = new Team(new TeamId("teamId"),
		                           new UserId("ownerId"),
		                           0,
		                           0);
		team.setDetails(teamDetails);
		team.setStatus(Status.ACTIVE);
		team.setPrivacy(Privacy.PUBLIC);
		team.setStatistics(new Statistics(10L, 10L, 10L));

		assertNotNull(team.getCreationDate());
		assertEquals(team.getDetails(), teamDetails);
		assertEquals(team.getStatus(), Status.ACTIVE);
		assertEquals(team.getPrivacy(), Privacy.PUBLIC);
		assertEquals(team.getStatistics(), new Statistics(10L, 10L, 10L));
		assertNotNull(team.getCreationDate());
	}

	@Test
	@DisplayName("Test team add active member")
	public void addActiveMembersTest() {
		final Team team = new Team(new TeamId("teamId"),
		                           new UserId("ownerId"),
		                           0,
		                           0);
		final UserId userId = new UserId("userId");
		final TeamMember result = team.addMember(userId, Status.ACTIVE);
		assertEquals(result.getTeamId(), team.getId());
		assertEquals(result.getUserId(), userId);
		assertEquals(result.getStatus(), Status.ACTIVE);
	}

	@Test
	@DisplayName("Test team is active or throw - is active ok")
	public void activeOrThrowOkIsActive() {
		final Team team = new Team(new TeamId("id"),
		                           new UserId("ownerId"),
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);
		assertDoesNotThrow(team::checkIsActiveOrThrow);
	}

	@Test
	@DisplayName("Test team is active or throw no ok")
	public void activeOrThrowOkIsInactive() {
		final Team team = new Team(new TeamId("id"),
		                           new UserId("ownerId"),
		                           0,
		                           0);
		team.setStatus(Status.INACTIVE);
		DomainException exception = assertThrows(DomainException.class, team::checkIsActiveOrThrow);
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test team is active or throw - is active ok")
	public void ownerOrThrowOkIsActive() {
		final UserId ownerId = new UserId("ownerId");
		final Team team = new Team(new TeamId("id"),
		                           ownerId,
		                           0,
		                           0);
		assertDoesNotThrow(() -> team.checkOwnerOrThrow(ownerId));
	}

	@Test
	@DisplayName("Test team is active or throw - is active ok")
	public void ownerOrThrowOkIsInactive() {
		final Team team = new Team(new TeamId("id"),
		                           new UserId("ownerId"),
		                           0,
		                           0);
		DomainException exception = assertThrows(DomainException.class, () -> team.checkOwnerOrThrow(new UserId("otherUserId")));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_OWNER_IS_NECESSARY);
	}
}
