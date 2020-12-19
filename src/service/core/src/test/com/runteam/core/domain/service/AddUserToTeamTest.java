package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddUserToTeamTest {

	@Test
	@DisplayName("Test add user to team ok")
	public void ok() {

		// User who wants to add user to team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Team to add the new user
		// ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(), ownerUserId);

		// User to add to the team
		final UserId newUserId = new UserId("newUserId");

		// Mock a list of teams already created by the user
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(ownerUserId)).thenReturn(ownerUser);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository);
		final Team result = addUserToTeam.add(ownerUserId, team, newUserId);

		assertEquals(result.getOwnerId(), ownerUser.getId());
		assertEquals(result.getActiveMembers().size(), 1);
	}

	@Test
	@DisplayName("Test add user to PUBLIC team ok")
	public void publicTeamOk() {

		// User who wants to add user to team
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// User owner of the team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Team to add the new user
		// Team is PUBLIC
		// ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(), ownerUserId);
		team.setPrivacy(Privacy.PUBLIC);

		// User to add to the team
		final UserId newUserId = new UserId("newUserId");

		// Mock a list of teams already created by the user
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository);
		final Team result = addUserToTeam.add(ownerUserId, team, newUserId);

		assertEquals(result.getOwnerId(), ownerUser.getId());
		assertEquals(result.getActiveMembers().size(), 1);
	}

	@Test
	@DisplayName("Test add user to private team no ok")
	public void privateTeamNoOk() {

		// User who wants to add user to team
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// User owner of the team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Team to add the new user
		// Team is PRIVATE
		// ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(), ownerUserId);
		team.setPrivacy(Privacy.PRIVATE);

		// User to add to the team
		final UserId newUserId = new UserId("newUserId");

		// Mock a list of teams already created by the user
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(managerUserId, team, newUserId));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_IS_PRIVATE);
	}

	@Test
	@DisplayName("Test too many members in team no ok")
	public void tooManyUsersOk() {

		// User who wants to add user to team
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// User owner of the team
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Team to add the new user
		// Team is PUBLIC
		// ownerUser is the owner of the team
		// Team has too many members
		final Team team = new Team(TeamId.randomId(), ownerUserId);
		team.setPrivacy(Privacy.PUBLIC);
		IntStream.range(0, ownerUser.getSubscriptionType().getMaxTeamsUserBelongs())
		         .forEach(i -> team.addMember(UserId.randomId(), OffsetDateTime.now()));

		// User to add to the team
		final UserId newUserId = new UserId("newUserId");

		// Mock a list of teams already created by the user
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(managerUserId, team, newUserId));
		assertEquals(exception.getCode(), DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS);
	}
}
