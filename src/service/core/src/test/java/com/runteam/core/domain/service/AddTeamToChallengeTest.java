package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.ChallengeMember;
import com.runteam.core.domain.model.ChallengeMemberId;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.ChallengeMemberRepository;
import com.runteam.core.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddTeamToChallengeTest {

	@Test
	@DisplayName("Test manager user is not the team owner - no ok")
	public void managerUserIsNotOwner() {

		final User managerUser = new User(new UserId("managerUserId"),
		                                  0,
		                                  0,
		                                  0);

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("challengeOwner"),
		                                          0);

		final Team team = new Team(new TeamId("teamId"),
		                           new UserId("owner"),
		                           0,
		                           0);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_OWNER_IS_NECESSARY);
	}

	@Test
	@DisplayName("Test manager user is not ACTIVE - no ok")
	public void managerUserIsNotActive() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.INACTIVE);

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("challengeOwner"),
		                                          0);

		final Team team = new Team(new TeamId("teamId"),
		                           managerUserId,
		                           0,
		                           0);

		final UserRepository userRepository = mock(UserRepository.class);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test team is not ACTIVE - no ok")
	public void teamIsNotActive() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.ACTIVE);

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("challengeOwner"),
		                                          0);

		final Team team = new Team(new TeamId("teamId"),
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.INACTIVE);

		final UserRepository userRepository = mock(UserRepository.class);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test challenge is not ACTIVE - no ok")
	public void challengeIsNotActive() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setStatus(Status.ACTIVE);

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("challengeOwner"),
		                                          0);
		challenge.setStatus(Status.INACTIVE);

		final Team team = new Team(new TeamId("teamId"),
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		final UserRepository userRepository = mock(UserRepository.class);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.CHALLENGE_IS_NOT_ACTIVE);
	}

	@Test
	@DisplayName("Test team in too many challenges - no ok")
	public void teamInTooManyChallenges() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final UserSubscriptionType subscriptionType = UserSubscriptionType.BASIC;
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(subscriptionType);
		managerUser.setStatus(Status.ACTIVE);

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("challengeOwner"),
		                                          0);
		challenge.setStatus(Status.ACTIVE);

		final Team team = new Team(new TeamId("teamId"),
		                           managerUserId,
		                           0,
		                           subscriptionType.getMaxChallenges());
		team.setStatus(Status.ACTIVE);

		// Mock the owner of the team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_IN_TOO_MANY_CHALLENGES);
	}

	@Test
	@DisplayName("Test team already in team - no ok")
	public void teamAlreadyInTeam() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		final Challenge challenge = new Challenge(new ChallengeId("challengeId"),
		                                          new UserId("challengeOwner"),
		                                          0);
		challenge.setStatus(Status.ACTIVE);

		final Team team = new Team(new TeamId("teamId"),
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of th team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock ChallengeMemberRepository - Team is ACTIVE in challenge
		final ChallengeMember member = new ChallengeMember(ChallengeMemberId.randomId(), challenge.getId(), team.getId());
		member.setStatus(Status.ACTIVE);
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);
		Mockito.when(challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), team.getId())).thenReturn(member);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);

		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.TEAM_ALREADY_IN_CHALLENGE);
	}

	@Test
	@DisplayName("Test add team to PUBLIC challenge ok")
	public void publicChallengeOk() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// Challenge to add the new team
		// Manager user is the owner of the challenge
		// Challenge is PUBLIC
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId,
		                                          managerUserId,
		                                          0);
		challenge.setPrivacy(Privacy.PUBLIC);
		challenge.setStatus(Status.ACTIVE);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId,
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);
		Mockito.when(challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), team.getId())).thenReturn(ChallengeMember.EMPTY);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);
		final ChallengeMember result = addTeamToChallenge.add(managerUser, challenge, team);

		assertEquals(result.getStatus(), Status.ACTIVE);
		assertEquals(result.getTeamId(), team.getId());
		assertEquals(result.getChallengeId(), challenge.getId());
	}

	@Test
	@DisplayName("Test add team to PRIVATE challenge - Manager User NOT owner of challenge - ok")
	public void privateChallengeNotOwnerOk() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// Challenge to add the new team
		// Manager user is NOT the owner of the challenge
		// Challenge is PUBLIC
		final UserId challengeOwnerUserId = new UserId("challengeOwnerId");
		final User challengeOwer = new User(challengeOwnerUserId,
		                                  0,
		                                  0,
		                                  0);
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId,
		                                          challengeOwnerUserId,
		                                          0);
		challenge.setPrivacy(Privacy.PRIVATE);
		challenge.setStatus(Status.ACTIVE);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId,
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of the team
		// challengeOwer is the owner of the challenge
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(challengeOwer);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);
		Mockito.when(challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), team.getId())).thenReturn(ChallengeMember.EMPTY);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);
		final ChallengeMember result = addTeamToChallenge.add(managerUser, challenge, team);

		assertEquals(result.getStatus(), Status.PENDING);
		assertEquals(result.getTeamId(), team.getId());
		assertEquals(result.getChallengeId(), challenge.getId());
	}

	@Test
	@DisplayName("Test add team to PRIVATE challenge - Manager User is owner of challenge - ok")
	public void privateChallengeOwnerOk() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(UserSubscriptionType.BASIC);
		managerUser.setStatus(Status.ACTIVE);

		// Challenge to add the new team
		// Manager user is the owner of the challenge
		// Challenge is PUBLIC
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId,
		                                          managerUserId,
		                                          0);
		challenge.setPrivacy(Privacy.PRIVATE);
		challenge.setStatus(Status.ACTIVE);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId,
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);
		Mockito.when(challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), team.getId())).thenReturn(ChallengeMember.EMPTY);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);
		final ChallengeMember result = addTeamToChallenge.add(managerUser, challenge, team);

		assertEquals(result.getStatus(), Status.ACTIVE);
		assertEquals(result.getTeamId(), team.getId());
		assertEquals(result.getChallengeId(), challenge.getId());
	}

	@Test
	@DisplayName("Test challenge has too many teams no ok")
	public void challengeHasTooManyTeamsNoOk() {

		// User who wants to add team to challenge
		final UserId managerUserId = new UserId("managerUserId");
		final UserSubscriptionType subscriptionType = UserSubscriptionType.BASIC;
		final User managerUser = new User(managerUserId,
		                                  0,
		                                  0,
		                                  0);
		managerUser.setSubscriptionType(subscriptionType);
		managerUser.setStatus(Status.ACTIVE);

		// Challenge to add the new team
		// Manager user is the owner of the challenge
		// Challenge is PUBLIC
		final ChallengeId challengeId = new ChallengeId("challengeId");
		final Challenge challenge = new Challenge(challengeId,
		                                          managerUserId,
		                                          subscriptionType.getMaxChallengeTeams());
		challenge.setPrivacy(Privacy.PUBLIC);
		challenge.setStatus(Status.ACTIVE);

		// Team to add to the challenge
		// managerUser is the owner of the team
		final TeamId teamId = new TeamId("teamId");
		final Team team = new Team(teamId,
		                           managerUserId,
		                           0,
		                           0);
		team.setStatus(Status.ACTIVE);

		// Mock the owner of the challenge and the team
		// managerUser is the owner of both challenge and team
		final UserRepository userRepository = mock(UserRepository.class);
		Mockito.when(userRepository.findById(challenge.getOwnerId())).thenReturn(managerUser);
		Mockito.when(userRepository.findById(team.getOwnerId())).thenReturn(managerUser);

		// Mock ChallengeMemberRepository - Team is not active in challenge
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);
		Mockito.when(challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), team.getId())).thenReturn(ChallengeMember.EMPTY);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);
		DomainException exception = assertThrows(DomainException.class, () -> addTeamToChallenge.add(managerUser, challenge, team));
		assertEquals(exception.getCode(), DomainExceptionCode.CHALLENGE_HAS_TOO_MANY_TEAMS);
	}
}
