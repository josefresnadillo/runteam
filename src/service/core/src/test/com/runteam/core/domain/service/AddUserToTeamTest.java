package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.TeamMember;
import com.runteam.core.domain.model.TeamMemberId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.TeamMemberRepository;
import com.runteam.core.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddUserToTeamTest {

	@Test
	@DisplayName("Test manager user is inactive no ok")
	public void managerUserInactiveNoOkTest() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.INACTIVE);

		final Team team = new Team(TeamId.randomId(),
		                           managerUser.getId(),
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);

		// Mock TeamMemberRepository with empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(managerUser, team, newUser));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test new user is inactive no ok")
	public void newUserInactiveNoOkTest() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.ACTIVE);

		final Team team = new Team(TeamId.randomId(),
		                           managerUser.getId(),
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.INACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);

		// Mock TeamMemberRepository with empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(managerUser, team, newUser));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test new user is inactive no ok")
	public void teamInactiveNoOkTest() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.ACTIVE);

		final Team team = new Team(TeamId.randomId(),
		                           managerUser.getId(),
		                           0,
		                           0);
		team.setStatus(Status.INACTIVE);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);

		// Mock TeamMemberRepository with empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(managerUser, team, newUser));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test add user to PUBLIC team ok")
	public void publicTeamOkTest() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// User owner of the team
		final User ownerUser = new User(new UserId("ownerUserId"),
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Team to add the new user to
		// Team is PUBLIC
		// Team is ACTIVE
		// Previous ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(),
		                           ownerUser.getId(),
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);
		team.setPrivacy(Privacy.PUBLIC);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Mock TeamMemberRepository with empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);
		Mockito.when(teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUser.getId())).thenReturn(TeamMember.EMPTY);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);
		final TeamMember newTeamMember = addUserToTeam.add(ownerUser, team, newUser);

		assertEquals(newTeamMember.getTeamId(), team.getId());
		assertEquals(newTeamMember.getUserId(), newUser.getId());
		assertEquals(newTeamMember.getStatus(), Status.ACTIVE);
	}

	@Test
	@DisplayName("Test add user to private team when manager is owner ok")
	public void privateTeamOkTest() {

		// User who wants to add a new user to the team
		final User ownerUser = new User(new UserId("ownerUserId"),
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Team to add the new user to
		// Team is PRIVATE
		// Team is ACTIVE
		// Previous ownerUser is the owner of the team
		final Team team = new Team(new TeamId("teamId"),
		                           ownerUser.getId(),
		                           0,
		                           0);
		team.setPrivacy(Privacy.PRIVATE);
		team.setStatus(Status.ACTIVE);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(ownerUser.getId())).thenReturn(ownerUser);

		// Mock TeamMemberRepository with empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);
		Mockito.when(teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUser.getId())).thenReturn(TeamMember.EMPTY);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);
		final TeamMember newTeamMember = addUserToTeam.add(ownerUser, team, newUser);

		assertEquals(newTeamMember.getTeamId(), team.getId());
		assertEquals(newTeamMember.getUserId(), newUser.getId());
		assertEquals(newTeamMember.getStatus(), Status.ACTIVE);
	}

	@Test
	@DisplayName("Test add user to private team when manager is not owner ok")
	public void privateTeamPendingOkTest() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0 );
		managerUser.setSubscriptionType(UserSubscriptionType.ADMIN);
		managerUser.setStatus(Status.ACTIVE);

		// User who wants to add a new user to the team
		final User ownerUser = new User(new UserId("ownerUserId"),
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Team to add the new user to
		// Team is PRIVATE
		// Team is ACTIVE
		// Previous ownerUser is the owner of the team
		final Team team = new Team(new TeamId("teamId"),
		                           ownerUser.getId(),
		                           0,
		                           0);
		team.setPrivacy(Privacy.PRIVATE);
		team.setStatus(Status.ACTIVE);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(ownerUser.getId())).thenReturn(ownerUser);

		// Mock TeamMemberRepository with empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);
		Mockito.when(teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUser.getId())).thenReturn(TeamMember.EMPTY);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);
		final TeamMember newTeamMember = addUserToTeam.add(managerUser, team, newUser);

		assertEquals(newTeamMember.getTeamId(), team.getId());
		assertEquals(newTeamMember.getUserId(), newUser.getId());
		assertEquals(newTeamMember.getStatus(), Status.PENDING);
	}

	@Test
	@DisplayName("Test add user already active in team no ok")
	public void addUserAlreadyActiveInTeamNoOk() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// User owner of the team
		final User ownerUser = new User(new UserId("ownerUserId"),
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Team to add the new user to
		// Team is ACTIVE
		// Team is PUBLIC
		// Previous ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(),
		                           ownerUser.getId(),
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);
		team.setPrivacy(Privacy.PUBLIC);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Mock TeamMemberRepository with an ACTIVE team member
		final TeamMember memberActive = new TeamMember(TeamMemberId.randomId(), team.getId(), newUser.getId());
		memberActive.setStatus(Status.ACTIVE); // here it is
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);
		Mockito.when(teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUser.getId())).thenReturn(memberActive);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(ownerUser, team, newUser));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_ALREADY_IN_TEAM);
	}

	@Test
	@DisplayName("Test too many members in team no ok")
	public void tooManyUsersInTeamOk() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// User owner of the team
		final User ownerUser = new User(new UserId("ownerUserId"),
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Team to add the new user to
		// Team is ACTIVE
		// Team is PUBLIC
		// Previous ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(),
		                           ownerUser.getId(),
		                           ownerUser.getSubscriptionType().getMaxTeamMembers(),
		                           0); // Max team member for subscription
		team.setStatus(Status.ACTIVE);
		team.setPrivacy(Privacy.PUBLIC);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              0,
		                              0,
		                              0);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Mock TeamMemberRepository with an empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);
		Mockito.when(teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUser.getId())).thenReturn(TeamMember.EMPTY);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(ownerUser, team, newUser));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_HAS_TOO_MANY_USERS);
	}

	@Test
	@DisplayName("Test too many members in team no ok")
	public void userInTooManyTeamsNoOk() {

		// User who wants to add the new user to team
		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// User owner of the team
		final User ownerUser = new User(new UserId("ownerUserId"),
		                                0,
		                                0,
		                                0);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		ownerUser.setStatus(Status.ACTIVE);

		// Team to add the new user to
		// Team is ACTIVE
		// Team is PUBLIC
		// Previous ownerUser is the owner of the team
		final Team team = new Team(TeamId.randomId(),
		                           ownerUser.getId(),
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);
		team.setPrivacy(Privacy.PUBLIC);

		// New User to add to the team
		final User newUser = new User(new UserId("newUserId"),
		                              UserSubscriptionType.BASIC.getMaxTeamsUserCanBelong(),
		                              0,
		                              0);
		newUser.setSubscriptionType(UserSubscriptionType.BASIC);
		newUser.setStatus(Status.ACTIVE);

		// Mock UserRepository with the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(ownerUser);

		// Mock TeamMemberRepository with an empty team member
		final TeamMemberRepository teamMemberRepository = mock(TeamMemberRepository.class);
		Mockito.when(teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUser.getId())).thenReturn(TeamMember.EMPTY);

		// Add user to team
		final AddUserToTeam addUserToTeam = new AddUserToTeam(userRepository, teamMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addUserToTeam.add(ownerUser, team, newUser));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IN_TOO_MANY_TEAMS);
	}
}
