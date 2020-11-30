package com.runteam.backend.domain;

import java.time.OffsetDateTime;

public class ChallengeMember {
    private final TeamId teamId;
    private final OffsetDateTime activeFrom;
    private final Statistics statistics;

    public ChallengeMember(final TeamId teamId,
                           final OffsetDateTime activeFrom,
                           final Statistics statistics){
        this.teamId = teamId;
        this.activeFrom = activeFrom;
        this.statistics = statistics;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public OffsetDateTime getActiveFrom() {
        return activeFrom;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
