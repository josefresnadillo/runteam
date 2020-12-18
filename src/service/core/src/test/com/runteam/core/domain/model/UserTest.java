package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	@DisplayName("Test team add details, status, activation date and privacy")
	public void addDetailsStatusPrivacyTest() {
		final UserCredentials userCredentials = new UserCredentials("username", "secret");
		final UserDetails userDetails = UserDetails.builder().build();

		final User user = new User(new UserId("userId"));
		user.setDetails(UserDetails.builder().build());
		user.setCredentials(userCredentials);
		user.setStatus(UserStatus.SUSPENDED);
		user.setPrivacy(Privacy.PUBLIC);
		user.setActivationDate(OffsetDateTime.MAX);
		user.setSubscriptionType(UserSubscriptionType.PREMIUM);

		assertEquals(user.getDetails(), userDetails);
		assertEquals(user.getCredentials(), userCredentials);
		assertEquals(user.getCredentials().getUsername(), userCredentials.getUsername());
		assertEquals(user.getCredentials().getSecret(), userCredentials.getSecret());
		assertEquals(user.getStatus(), UserStatus.SUSPENDED);
		assertEquals(user.getPrivacy(), Privacy.PUBLIC);
		assertEquals(user.getActivationDate(), OffsetDateTime.MAX);
		assertEquals(user.getSubscriptionType(), UserSubscriptionType.PREMIUM);
	}


	@Test
	@DisplayName("Test team add statistics")
	public void AddStatisticsTest() {
		final User user = new User(new UserId("id"));
		user.addStatistics(new Statistics(10L, 10L, 10L));
		user.addStatistics(new Statistics(5L, 5L, 5L));

		assertEquals(user.getStatistics().getTotalMeters(), 15);
		assertEquals(user.getStatistics().getTotalSeconds(), 15);
		assertEquals(user.getStatistics().getElevationInMeters(), 15);
	}
}
