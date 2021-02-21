package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiChallengeAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.ChallengeRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.CreateChallenge;

import java.time.OffsetDateTime;

public class CreateChallengeUseCase {

    private final CreateChallenge createChallenge;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ApiChallengeAdapter apiChallengeAdapter;

    public CreateChallengeUseCase(final CreateChallenge createChallenge,
                                  final UserRepository userRepository,
                                  final ChallengeRepository challengeRepository,
                                  final ApiChallengeAdapter apiChallengeAdapter) {
        this.createChallenge = createChallenge;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.apiChallengeAdapter = apiChallengeAdapter;
    }

    public com.runteam.core.api.Challenge createActive(final String userId,
                                                       final String name,
                                                       final String displayName,
                                                       final com.runteam.core.api.ChallengeDetails apiDetails,
                                                       final com.runteam.core.api.ChallengeGoal apiGoal) {

        final User owner = this.userRepository.findById(new UserId(userId));

        final ChallengeDetails details = ChallengeDetails.builder()
                .name(name)
                .displayName(displayName)
                .imageUrl(apiDetails.getImageUrl())
                .tags(apiDetails.getTags())
                .build();

        final ChallengeGoal goal = ChallengeGoal.builder()
                .meters(apiGoal.getMeters())
                .elevationInMeters(apiGoal.getElevationInMeters())
                .activeFrom(OffsetDateTime.parse(apiGoal.getActiveFrom()))
                .activeTo(OffsetDateTime.parse(apiGoal.getActiveTo()))
                .build();

        final Challenge newChallenge = this.createChallenge.active(owner, details, goal);

        this.challengeRepository.save(newChallenge);

        return this.apiChallengeAdapter.adaptFromDomain(newChallenge, owner);
    }
}
