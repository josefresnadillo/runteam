package com.runteam.core.application;

import com.runteam.core.api.Challenge;
import com.runteam.core.api.ChallengeDetails;
import com.runteam.core.api.ChallengeGoal;
import com.runteam.core.application.adapter.ApiChallengeAdapter;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.ChallengeRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.CreateChallenge;
import java.time.OffsetDateTime;

public class ChallengeService {

    private final CreateChallenge createChallenge;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ApiChallengeAdapter apiChallengeAdapter;

    public ChallengeService(final CreateChallenge createChallenge,
                            final UserRepository userRepository,
                            final ChallengeRepository challengeRepository,
                            final ApiChallengeAdapter apiChallengeAdapter) {
        this.createChallenge = createChallenge;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.apiChallengeAdapter = apiChallengeAdapter;
    }

    public Challenge createActiveChallenge(final String userId,
                                           final String name,
                                           final String displayName,
                                           final ChallengeDetails apiDetails,
                                           final ChallengeGoal apiGoal) {

        final com.runteam.core.domain.model.User owner = userRepository.findById(new UserId(userId));

        final com.runteam.core.domain.model.ChallengeDetails details = com.runteam.core.domain.model.ChallengeDetails.builder()
                .name(name)
                .displayName(displayName)
                .imageUrl(apiDetails.getImageUrl())
                .tags(apiDetails.getTags())
                .build();

        final com.runteam.core.domain.model.ChallengeGoal goal = com.runteam.core.domain.model.ChallengeGoal.builder()
                .meters(apiGoal.getMeters())
                .elevationInMeters(apiGoal.getElevationInMeters())
                .activeFrom(OffsetDateTime.parse(apiGoal.getActiveFrom()))
                .activeTo(OffsetDateTime.parse(apiGoal.getActiveTo()))
                .build();

        final com.runteam.core.domain.model.Challenge newChallenge = this.createChallenge.active(owner, details, goal);

        this.challengeRepository.save(newChallenge);

        return this.apiChallengeAdapter.adaptFromDomain(newChallenge, owner);
    }
}
