package com.runteam.core.domain.event;

import com.runteam.core.domain.model.Statistics;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class StatisticsEvent {
    private final String id;
    private final OffsetDateTime date;
    private final Statistics statistics;

    public StatisticsEvent(final OffsetDateTime date,
                           final Statistics statistics){
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.statistics = statistics;
    }

    public String getId() {
        return id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsEvent that = (StatisticsEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(statistics, that.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, statistics);
    }

    @Override
    public String toString() {
        return "StatisticsUserEvent{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", statistics=" + statistics +
                '}';
    }
}
