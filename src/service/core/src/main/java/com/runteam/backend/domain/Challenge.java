package com.runteam.backend.domain;

import java.time.OffsetDateTime;
import java.util.List;

// Aggregate

public class Challenge {
    private final ChallengeId id;
    private final UserId owner;
    private final ChallengeDetails details;
    private final OffsetDateTime activationDate;
    private final ChallengeGoal goal;
    private final ChallengeStatus status;
    private final List<ChallengeMember> activeTeams;
    private final List<ChallengeMember> inactiveTeams;
    private final List<ChallengeMember> applicantTeams;

}
