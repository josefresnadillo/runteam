package com.runteam.core.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

// Value Object

public class TeamMember {
    private final UserId userId;
    private final OffsetDateTime activeFrom;
    private final OffsetDateTime activeTo;
    private final Statistics statistics;

    public TeamMember(final UserId userId,
                      final OffsetDateTime activeFrom,
                      final OffsetDateTime activeTo,
                      final Statistics statistics) {
        this.userId = userId;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
        this.statistics = statistics;
    }

    public UserId getUserId() {
        return userId;
    }

    public OffsetDateTime getActiveFrom() {
        return activeFrom;
    }

    public OffsetDateTime getActiveTo() {
        return activeTo;
    }

    public Statistics getStatistics() {
        return isActive() ? statistics : new Statistics(0L, 0L, 0L);
    }

    public boolean isActive(){
        return this.activeTo.isAfter(OffsetDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(activeFrom, that.activeFrom) &&
                Objects.equals(activeTo, that.activeTo) &&
                Objects.equals(statistics, that.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, activeFrom, activeTo, statistics);
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "userId=" + userId +
                ", activeFrom=" + activeFrom +
                ", activeTo=" + activeTo +
                ", statistics=" + statistics +
                '}';
    }
}
