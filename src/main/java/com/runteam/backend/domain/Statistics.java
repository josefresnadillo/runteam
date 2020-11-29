package com.runteam.backend.domain;

import java.util.Objects;

public class Statistics {
    private final Long totalMeters;
    private final Long totalSeconds;

    public Statistics(final Long totalMeters, final Long totalSeconds){
        this.totalMeters = totalMeters;
        this.totalSeconds = totalSeconds;
    }

    public Long getTotalMeters() {
        return totalMeters;
    }

    public Long getTotalSeconds() {
        return totalSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(totalMeters, that.totalMeters) &&
                Objects.equals(totalSeconds, that.totalSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMeters, totalSeconds);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "totalMeters=" + totalMeters +
                ", totalSeconds=" + totalSeconds +
                '}';
    }
}
