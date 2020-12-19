package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamDetails;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.TeamStatus;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateTeamTest {

	@Test
	@DisplayName("Test create team ok")
	public void ok() {

		// User who wants to create a team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Mock a list of teams already created by the user
		final TeamRepository teamRepository = mock(TeamRepository.class);
		final Team team = new Team(new TeamId("teamId"), ownerUserId);
		Mockito.when(teamRepository.findByOwnerId(ownerUserId)).thenReturn(List.of(team));

		// Create a team with details
		final TeamDetails teamDetails = TeamDetails.builder()
		                                           .name("team1")
		                                           .displayName("The Best Team")
		                                           .city("Madrid")
		                                           .countryCode("es")
		                                           .build();
		final CreateTeam createTeam = new CreateTeam(teamRepository);
		final Team result = createTeam.active(ownerUser, teamDetails);

		assertEquals(result.getOwnerId(), ownerUser.getId());
		assertEquals(result.getStatus(), TeamStatus.ACTIVE);
		assertEquals(result.getDetails(), teamDetails);
	}

	@Test
	@DisplayName("Test create team too many teams")
	public void tooManyTeams() {

		// User who wants to create a team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// create as many teams as the BASIC subscriptions allows
		final List<Team> teams = new ArrayList<>();
		IntStream.range(0, UserSubscriptionType.BASIC.getMaxTeams())
		         .forEach(i -> teams.add(new Team(TeamId.randomId(), ownerUserId)));

		// Mock a list of team already created by the user
		final TeamRepository teamRepository = mock(TeamRepository.class);
		Mockito.when(teamRepository.findByOwnerId(ownerUserId)).thenReturn(teams);

		// Create a team
		final CreateTeam createTeam = new CreateTeam(teamRepository);
		DomainException exception = assertThrows(DomainException.class, () -> createTeam.active(ownerUser, TeamDetails.builder().build()));
		assertEquals(exception.getCode(), DomainExceptionCode.TOO_MANY_TEAMS);
	}
}
