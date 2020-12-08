package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.ChallengeRepository;

import java.util.List;
import java.util.logging.Logger;

public class CreateChallenge {
    private static final Logger LOGGER = Logger.getLogger(CreateChallenge.class.toString());

    private final ChallengeRepository challengeRepository;

    public CreateChallenge(final ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Challenge active(final User user,
                            final ChallengeDetails details,
                            final ChallengeGoal goal) {
        final List<Challenge> challenges = challengeRepository.findByOwnerId(user.getId());

        if (challenges.size() >= user.getSubscriptionType().getMaxChallenges()) {
            LOGGER.info(DomainExceptionCode.TOO_MUCH_CHALLENGES.getMsg() + ": " + user);
            throw new DomainException(DomainExceptionCode.TOO_MUCH_CHALLENGES);
        }

        final Challenge challenge = new Challenge(ChallengeId.randomChallengeId(), user.getId());
        challenge.setDetails(details);
        challenge.setGoal(goal);
        challenge.setStatus(ChallengeStatus.ACTIVE);
        return challenge;
    }
}
