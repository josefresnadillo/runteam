package com.runteam.core.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeDetails;
import com.runteam.core.domain.model.ChallengeGoal;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.model.UserSubscriptionType;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateChallengeTest {

    @Test
    @DisplayName("Test create challenge ok")
    public void ok() {

        // User who wants to create a challenge
        final UserId ownerUserId = new UserId("ownerUserId");
        final User ownerUser = new User(ownerUserId, 0,
                0,
                0);
        ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
        ownerUser.setStatus(Status.ACTIVE);

        // Challenge details
        final ChallengeDetails challengeDetails = ChallengeDetails.builder()
                .name("challenge1")
                .displayName("The Best Challenge")
                .build();

        // Challenge goal
        final ChallengeGoal challengeGoal = ChallengeGoal.builder()
                .meters(42000L)
                .elevationInMeters(1000L)
                .activeFrom(OffsetDateTime.now())
                .activeTo(OffsetDateTime.now().plusDays(10))
                .build();

        // Create challenge
        final CreateChallenge createChallenge = new CreateChallenge();
        final Challenge result = createChallenge.active(ownerUser,
                challengeDetails,
                challengeGoal);

        assertEquals(result.getOwnerId(), ownerUser.getId());
        assertEquals(result.getStatus(), Status.ACTIVE);
        assertEquals(result.getDetails(), challengeDetails);
        assertEquals(result.getGoal(), challengeGoal);
    }

    @Test
    @DisplayName("Test manager user is not active")
    public void userIsNotActive() {

        // User who wants to create a challenge
        final UserId ownerUserId = new UserId("ownerUserId");
        final User ownerUser = new User(ownerUserId, 0,
                0,
                UserSubscriptionType.BASIC.getMaxChallenges());
        ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
        ownerUser.setStatus(Status.INACTIVE);

        // Create challenge
        final CreateChallenge createChallenge = new CreateChallenge();
        DomainException exception = assertThrows(DomainException.class, () -> createChallenge.active(ownerUser,
                ChallengeDetails.builder().build(),
                ChallengeGoal.zero()));
        assertEquals(exception.getCode(), DomainExceptionCode.USER_IS_NOT_ACTIVE);
    }

    @Test
    @DisplayName("Test user has created too many challenge")
    public void tooManyChallenges() {

        // User who wants to create a challenge
        final UserId ownerUserId = new UserId("ownerUserId");
        final User ownerUser = new User(ownerUserId, 0,
                0,
                UserSubscriptionType.BASIC.getMaxChallenges());
        ownerUser.setSubscriptionType(UserSubscriptionType.BASIC);
        ownerUser.setStatus(Status.ACTIVE);

        // Create challenge
        final CreateChallenge createChallenge = new CreateChallenge();
        DomainException exception = assertThrows(DomainException.class, () -> createChallenge.active(ownerUser,
                ChallengeDetails.builder().build(),
                ChallengeGoal.zero()));
        assertEquals(exception.getCode(), DomainExceptionCode.USER_CREATED_TOO_MANY_CHALLENGES);
    }
}
