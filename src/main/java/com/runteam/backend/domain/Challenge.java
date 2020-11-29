package com.runteam.backend.domain;

import java.time.OffsetDateTime;
import java.util.List;

public class Challenge {
    private final ChallengeId id;
    private final String name;
    private final String displayName;
    private final OffsetDateTime activeFrom;
    private final ChallengeGoal goal;
    private final ChallengeStatus status;
    private final List<ChallengeMember> teams;
    private final Statistics statistics;

    public Challenge(final ChallengeId id,
                     final String name,
                     final String displayName,
                     final OffsetDateTime activeFrom,
                     final ChallengeStatus status,
                     final ChallengeGoal goal,
                     final List<ChallengeMember> teams,
                     final Statistics statistics){
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.activeFrom = activeFrom;
        this.status = status;
        this.goal = goal;
        this.teams = teams;
        this.statistics = statistics;
    }

    public ChallengeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public OffsetDateTime getActiveFrom() {
        return activeFrom;
    }

    public ChallengeGoal getGoal() {
        return goal;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public List<ChallengeMember> getTeams() {
        return teams;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
