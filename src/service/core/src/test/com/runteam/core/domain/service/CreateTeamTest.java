package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamDetails;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTeamTest {

	@Test
	@DisplayName("Test create team ok")
	public void ok() {

		// User who wants to create a team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId,
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Create a team with details
		final TeamDetails teamDetails = TeamDetails.builder()
		                                           .name("team1")
		                                           .displayName("The Best Team")
		                                           .city("Madrid")
		                                           .countryCode("es")
		                                           .build();
		final CreateTeam createTeam = new CreateTeam();
		final Team result = createTeam.active(ownerUser, teamDetails);

		assertEquals(result.getOwnerId(), ownerUser.getId());
		assertEquals(result.getStatus(), Status.ACTIVE);
		assertEquals(result.getDetails(), teamDetails);
	}

	@Test
	@DisplayName("Test manager user is inactive")
	public void userIsInactive() {

		// User who wants to create a team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId,
		                                0,
		                                UserSubscriptionType.BASIC.getMaxTeams(),
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.INACTIVE);

		// Create a team
		final CreateTeam createTeam = new CreateTeam();
		DomainException exception = assertThrows(DomainException.class, () -> createTeam.active(ownerUser, TeamDetails.builder().build()));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test manager user has created too many teams")
	public void tooManyTeams() {

		// User who wants to create a team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId,
		                                0,
		                                UserSubscriptionType.BASIC.getMaxTeams(),
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Create a team
		final CreateTeam createTeam = new CreateTeam();
		DomainException exception = assertThrows(DomainException.class, () -> createTeam.active(ownerUser, TeamDetails.builder().build()));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_CREATED_TOO_MANY_TEAMS);
	}
}
