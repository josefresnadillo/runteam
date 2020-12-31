package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.ChallengeMember;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.ChallengeMemberRepository;
import com.runteam.core.domain.repository.ChallengeRepository;
import com.runteam.core.domain.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddTeamToChallengeTest {

	@Test
	@DisplayName("Test add team to challenge ok")
	public void ok() {

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

		// Mock a list of challenges the team belongs to - none
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByTeamId(team.getId())).thenReturn(List.of());

		// Mock a list of challenges the team belongs to - none
		final ChallengeMemberRepository challengeMemberRepository = mock(ChallengeMemberRepository.class);
		Mockito.when(challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), team.getId())).thenReturn(ChallengeMember.EMPTY);

		// Add team to challenge
		final AddTeamToChallenge addTeamToChallenge = new AddTeamToChallenge(userRepository, challengeMemberRepository);
		final ChallengeMember result = addTeamToChallenge.add(managerUser, challenge, team);

		assertEquals(result.getStatus(), Status.ACTIVE);
		assertEquals(result.getTeamId(), team.getId());
		assertEquals(result.getChallengeId(), challenge.getId());
	}
}
