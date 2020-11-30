package com.runteam.backend.domain;

import java.time.OffsetDateTime;

public class TeamMember {
    private final UserId userId;
    private final OffsetDateTime activeFrom;
    private final Statistics statistics;

    public TeamMember(final UserId userId,
                      final OffsetDateTime activeFrom,
                      final Statistics statistics){
        this.userId = userId;
        this.activeFrom = activeFrom;
        this.statistics = statistics;
    }

    public UserId getUserId() {
        return userId;
    }

    public OffsetDateTime getActiveFrom() {
        return activeFrom;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
