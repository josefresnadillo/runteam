package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.ChallengeRepository;
import com.runteam.core.domain.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddTeamToChallengeTest {

	@Test
	@DisplayName("Test add team to challenge ok")
	public void ok() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Challenge to add the new team
		// Manager user is the owner of the challenge
		// Challenge is PUBLIC
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId, managerUserId);
		challenge.setPrivacy(Privacy.PUBLIC);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId, managerUserId);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock a list of challenges the team belongs to - none
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByTeamId(team.getId())).thenReturn(List.of());

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeRepository);
		final Challenge result = addTeamToChallenge.add(managerUserId, challenge, team);

		assertEquals(result.getOwnerId(), managerUser.getId());
		assertEquals(result.getActiveMembers().size(), 1);
	}

	@Test
	@DisplayName("Test user is not the owner of the team no ok")
	public void userNotOwnerNoOk() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Challenge to add the new team
		// Challenge is PRIVATE
		final UserId challengeOwnerUserId = new UserId("challengeOwnerUserId");
		final User challengeOwnerUser = new User(challengeOwnerUserId);
		challengeOwnerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId, challengeOwnerUserId);
		challenge.setPrivacy(Privacy.PRIVATE);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final UserId teamOwnerId = new UserId("teamOwnerId");
		final User teamOwner = new User(teamOwnerId);
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId, teamOwnerId);

		// Mock the owner of the challenge and the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(challengeOwnerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(teamOwner);

		// Mock a list of challenges the team belongs to - none
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByTeamId(team.getId())).thenReturn(List.of());

		// Add user to team
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUserId, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.ONLY_OWNER_CAN_ADD_TEAM_TO_CHALLENGE);
	}

	@Test
	@DisplayName("Test private challenge no ok")
	public void privateChallengeNoOk() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Challenge to add the new team
		// Challenge is PRIVATE
		final UserId challengeOwnerUserId = new UserId("challengeOwnerUserId");
		final User challengeOwnerUser = new User(challengeOwnerUserId);
		challengeOwnerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId, challengeOwnerUserId);
		challenge.setPrivacy(Privacy.PRIVATE);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId, managerUserId);

		// Mock the owner of the challenge and the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(challengeOwnerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock a list of challenges the team belongs to - none
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByTeamId(team.getId())).thenReturn(List.of());

		// Add user to team
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUserId, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.CHALLENGE_IS_PRIVATE);
	}

	@Test
	@DisplayName("Test challenge has too many teams ok")
	public void challengeTooManyTeams() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Challenge to add the new team
		// Challenge has already too many teams
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId, managerUserId);
		challenge.setPrivacy(Privacy.PUBLIC);
		IntStream.range(0, managerUser.getSubscriptionType().getMaxChallengeTeams())
		         .forEach(i -> challenge.addMember(TeamId.randomId(), OffsetDateTime.now()));

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId, managerUserId);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock a list of challenges the team belongs to - none
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByTeamId(team.getId())).thenReturn(List.of());

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUserId, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.CHALLENGE_HAS_TOO_MANY_TEAMS);
	}

	@Test
	@DisplayName("Test team in too many challenges ok")
	public void teamInTooManyChallenges() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Challenge to add the new team
		// Challenge has already too many teams
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId, managerUserId);
		challenge.setPrivacy(Privacy.PUBLIC);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId, managerUserId);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock a list of challenges the team belongs to - too many
		final List<Challenge> challenges = new ArrayList<>();
		IntStream.range(0, managerUser.getSubscriptionType().getMaxChallengesTeamBelongs())
		         .forEach(i -> challenges.add(new Challenge(ChallengeId.randomId(), managerUserId)));
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByTeamId(team.getId())).thenReturn(challenges);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUserId, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_IN_TOO_MANY_CHALLENGES);
	}
}
