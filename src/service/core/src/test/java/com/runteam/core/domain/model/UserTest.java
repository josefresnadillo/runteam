package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	@DisplayName("Test team add details, status, activation date and privacy")
	public void okTest() {
		final UserCredentials userCredentials = new UserCredentials("username", "secret");
		final UserDetails userDetails = UserDetails.builder().build();

		final User user = new User(new UserId("userId"),
		                           0,
		                           0,
		                           0);
		user.setDetails(UserDetails.builder().build());
		user.setCredentials(userCredentials);
		user.setStatus(Status.SUSPENDED);
		user.setPrivacy(Privacy.PUBLIC);
		user.setSubscriptionType(UserSubscriptionType.PREMIUM);

		assertNotNull(user.getCreationDate());
		assertEquals(user.getNumberOfMemberShips(), 0);
		assertEquals(user.getNumberOfTeamsCreated(), 0);
		assertEquals(user.getNumberOfChallengesCreated(), 0);
		assertEquals(user.getDetails(), userDetails);
		assertEquals(user.getCredentials(), userCredentials);
		assertEquals(user.getCredentials().getUsername(), userCredentials.getUsername());
		assertEquals(user.getCredentials().getSecret(), userCredentials.getSecret());
		assertEquals(user.getStatus(), Status.SUSPENDED);
		assertEquals(user.getPrivacy(), Privacy.PUBLIC);
		assertEquals(user.getSubscriptionType(), UserSubscriptionType.PREMIUM);
	}

	@Test
	@DisplayName("Test user add statistics")
	public void addStatisticsTest() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           0);
		user.addStatistics(new Statistics(1000L, 10L, 300L));
		user.addStatistics(new Statistics(1100L, 5L, 400L));

		assertEquals(user.getStatistics().getTotalMeters().longValue(), 2100L);
		assertEquals(user.getStatistics().getTotalSeconds().longValue(), 700L);
		assertEquals(user.getStatistics().getElevationInMeters().longValue(), 15L);
	}

	@Test
	@DisplayName("Test user add statistics and personal best")
	public void personalBestTest() {
		final User user = new User(new UserId("id"),
				0,
				0,
				0);
		user.addStatistics(new Statistics(1000L, 10L, 300L));
		user.addStatistics(new Statistics(1000L, 5L, 400L));

		assertEquals(user.getPersonalBest().getBest1kInSeconds().longValue(), 300L);
	}

	@Test
	@DisplayName("Test user is active or throw - is active ok")
	public void activeOrThrowOkIsActive() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           0);
		user.setStatus(Status.ACTIVE);
		assertDoesNotThrow(user::checkIsActiveOrThrow);
	}

	@Test
	@DisplayName("Test user is active or throw - is active ok")
	public void activeOrThrowOkIsInactive() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           0);
		user.setStatus(Status.INACTIVE);
		DomainException exception = assertThrows(DomainException.class, user::checkIsActiveOrThrow);
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test user number of teams created or throw ok")
	public void numberOfTeamsCreatedOrThrowOk() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           0);
		assertDoesNotThrow(user::checkNumberOfTeamsCreatedOrThrow);
	}

	@Test
	@DisplayName("Test user number of teams created or throw no ok")
	public void numberOfTeamsCreatedOrThrowNoOk() {
		final User user = new User(new UserId("id"),
		                           0,
		                           UserSubscriptionType.BASIC.getMaxTeams() + 1,
		                           0);
		DomainException exception = assertThrows(DomainException.class, user::checkNumberOfTeamsCreatedOrThrow);
		assertEquals(exception.getCode(), DomainExceptionCode.USER_CREATED_TOO_MANY_TEAMS);
	}

	@Test
	@DisplayName("Test user number of challenges created or throw ok")
	public void numberOfChallengesCreatedOrThrowOk() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           0);
		assertDoesNotThrow(user::checkNumberOfChallengesCreatedOrThrow);
	}

	@Test
	@DisplayName("Test user number of challenges created or throw no ok")
	public void numberOfChallengesCreatedOrThrowNoOk() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           UserSubscriptionType.BASIC.getMaxChallenges() + 1);
		DomainException exception = assertThrows(DomainException.class, user::checkNumberOfChallengesCreatedOrThrow);
		assertEquals(exception.getCode(), DomainExceptionCode.USER_CREATED_TOO_MANY_CHALLENGES);
	}

	@Test
	@DisplayName("Test user number of team memberships or throw ok")
	public void numberOfMembershipsOrThrowOk() {
		final User user = new User(new UserId("id"),
		                           0,
		                           0,
		                           0);
		assertDoesNotThrow(user::checkNumberOfMembershipsOrThrow);
	}

	@Test
	@DisplayName("Test user number of team memberships or throw no ok")
	public void numberOfMembershipsOrThrowNoOk() {
		final User user = new User(new UserId("id"),
		                           UserSubscriptionType.BASIC.getMaxTeamsUserCanBelong() + 1,
		                           0,
		                           0);
		DomainException exception = assertThrows(DomainException.class, user::checkNumberOfMembershipsOrThrow);
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IN_TOO_MANY_TEAMS);
	}
}
