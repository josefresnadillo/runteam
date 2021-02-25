package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class AddStatisticsToUserTest {

	@Test
	@DisplayName("Test manager user add statistics ok")
	public void managerUserActiveOkTest() {

		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.ACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final DomainEvents domainEvents = mock(DomainEvents.class);

		// Add user to team
		final AddStatisticsToUser addStatisticsToUser = new AddStatisticsToUser(domainEvents);
		final User result = addStatisticsToUser.add(managerUser, statistics, OffsetDateTime.now());

		assertEquals(result.getStatistics(), statistics);
	}

	@Test
	@DisplayName("Test manager user add statistics no active no ok")
	public void managerUserInactiveNoOkTest() {

		final User managerUser = new User(new UserId("managerUserId"),
				0,
				0,
				0);
		managerUser.setStatus(Status.INACTIVE);

		final Statistics statistics = new Statistics(10L, 10L, 10L);

		final DomainEvents domainEvents = mock(DomainEvents.class);

		// Add user to team
		final AddStatisticsToUser addStatisticsToUser = new AddStatisticsToUser(domainEvents);

		DomainException exception = assertThrows(DomainException.class, () -> addStatisticsToUser.add(managerUser, statistics, OffsetDateTime.now()));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
	}
}
