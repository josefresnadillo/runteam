package com.runteam.core.domain.event;

import com.runteam.core.domain.model.Statistics;
import com.runteam.core.domain.model.UserId;
import java.time.OffsetDateTime;
import java.util.Objects;

public class StatisticsUserEvent extends StatisticsEvent {
    private final UserId userId;

    public StatisticsUserEvent(final UserId userId,
                               final OffsetDateTime date,
                               final Statistics statistics) {
        super(date, statistics);
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StatisticsUserEvent that = (StatisticsUserEvent) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId);
    }

    @Override
    public String toString() {
        return "StatisticsUserEvent{" +
                "id='" + getId() + '\'' +
                ", date=" + getDate() +
                ", statistics=" + getStatistics() +
                ", userId=" + userId +
                '}';
    }
}
