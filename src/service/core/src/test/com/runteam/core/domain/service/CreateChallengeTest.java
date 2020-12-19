package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeDetails;
import com.runteam.core.domain.model.ChallengeGoal;
import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.ChallengeStatus;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.ChallengeRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateChallengeTest {

	@Test
	@DisplayName("Test create challenge ok")
	public void ok() {

		// User who wants to create a challenge
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// Mock a list of challenges already created by the user
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		final Challenge challenge = new Challenge(ChallengeId.randomId(), ownerUserId);
		Mockito.when(challengeRepository.findByOwnerId(ownerUserId)).thenReturn(List.of(challenge));

		// Challenge details and goal
		final ChallengeDetails challengeDetails = ChallengeDetails.builder()
		                                                          .name("challenge1")
		                                                          .displayName("The Best Challenge")
		                                                          .build();
		final ChallengeGoal challengeGoal = new ChallengeGoal(42000L,
		                                                      1000L,
		                                                      OffsetDateTime.now(),
		                                                      OffsetDateTime.now().plusDays(10));

		// Create challenge
		final CreateChallenge createChallenge = new CreateChallenge(challengeRepository);
		final Challenge result = createChallenge.active(ownerUser,
		                                                challengeDetails,
		                                                challengeGoal);

		assertEquals(result.getOwnerId(), ownerUser.getId());
		assertEquals(result.getStatus(), ChallengeStatus.ACTIVE);
		assertEquals(result.getDetails(), challengeDetails);
		assertEquals(result.getGoal(), challengeGoal);
	}

	@Test
	@DisplayName("Test create challenge too many challenge")
	public void tooManyChallenges() {

		// User who wants to create a challenge
		final UserId ownerUserId = new UserId("ownerUserId");
		final User ownerUser = new User(ownerUserId);
		ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);

		// create as many challenges as the BASIC subscriptions allows
		final List<Challenge> challenges = new ArrayList<>();
		IntStream.range(0, ownerUser.getSubscriptionType().getMaxTeams())
		         .forEach(i -> challenges.add(new Challenge(ChallengeId.randomId(), ownerUserId)));

		// Mock a list of challenge already created by the user
		final ChallengeRepository challengeRepository = mock(ChallengeRepository.class);
		Mockito.when(challengeRepository.findByOwnerId(ownerUserId)).thenReturn(challenges);

		// Create challenge
		final CreateChallenge createChallenge = new CreateChallenge(challengeRepository);
		DomainException exception = assertThrows(DomainException.class, () -> createChallenge.active(ownerUser,
		                                                                 ChallengeDetails.builder().build(),
		                                                                 ChallengeGoal.zero()));
		assertEquals(exception.getCode(), DomainExceptionCode.TOO_MANY_CHALLENGES);
	}
}
