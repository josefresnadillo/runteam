package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

// Value Object

public class ChallengeMember {

    public static final OffsetDateTime MIN_TEAM_MEMBER_VALID_TO = OffsetDateTime.of(1970,
            1,
            1,
            0,
            0,
            0,
            0,
            ZoneOffset.UTC);

    public static final OffsetDateTime MAX_TEAM_MEMBER_VALID_TO = OffsetDateTime.of(2048,
            1,
            1,
            0,
            0,
            0,
            0,
            ZoneOffset.UTC);

    public static final ChallengeMember EMPTY = new ChallengeMember(TeamId.EMPTY,
            MIN_TEAM_MEMBER_VALID_TO,
            MIN_TEAM_MEMBER_VALID_TO,
            Statistics.EMPTY) {
        public boolean isEmpty() {
            return true;
        }
    };

    private final TeamId teamId;
    private final OffsetDateTime activeFrom;
    private final OffsetDateTime activeTo;
    private final Statistics statistics;

    public ChallengeMember(final TeamId teamId,
                           final OffsetDateTime activeFrom,
                           final OffsetDateTime activeTo,
                           final Statistics statistics) {
        this.teamId = teamId;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
        this.statistics = statistics;
    }

    public TeamId getTeamId() {
        return teamId;
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

    public Statistics addStatistics(final Statistics statistics) {
        return this.statistics.add(statistics);
    }

    public boolean isActive() {
        return this.activeTo.isAfter(OffsetDateTime.now());
    }

    public boolean isInactive() {
        return this.activeTo.isBefore(OffsetDateTime.now());
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeMember that = (ChallengeMember) o;
        return Objects.equals(teamId, that.teamId) &&
                Objects.equals(activeFrom, that.activeFrom) &&
                Objects.equals(activeTo, that.activeTo) &&
                Objects.equals(statistics, that.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, activeFrom, statistics);
    }

    @Override
    public String toString() {
        return "ChallengeMember{" +
                "teamId=" + teamId +
                ", activeFrom=" + activeFrom +
                ", activeFrom=" + activeTo +
                ", statistics=" + statistics +
                '}';
    }
}
