package com.runteam.core.domain.event;

import com.runteam.core.domain.model.Statistics;
import com.runteam.core.domain.model.TeamMemberId;
import java.time.OffsetDateTime;
import java.util.Objects;

public class StatisticsTeamMemberEvent extends StatisticsEvent {
    private final TeamMemberId teamMemberId;

    public StatisticsTeamMemberEvent(final TeamMemberId teamMemberId,
                                     final OffsetDateTime date,
                                     final Statistics statistics) {
        super(date, statistics);
        this.teamMemberId = teamMemberId;
    }

    public TeamMemberId getTeamMemberId() {
        return teamMemberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StatisticsTeamMemberEvent that = (StatisticsTeamMemberEvent) o;
        return Objects.equals(teamMemberId, that.teamMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teamMemberId);
    }

    @Override
    public String toString() {
        return "StatisticsTeamMemberEvent{" +
                "id='" + getId() + '\'' +
                ", date=" + getDate() +
                ", statistics=" + getStatistics() +
                ", teamMemberId=" + getTeamMemberId() +
                '}';
    }
}
