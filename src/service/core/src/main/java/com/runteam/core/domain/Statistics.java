package com.runteam.core.domain;

// Value Object

import java.util.Objects;

public class Statistics {

    public static final Statistics EMPTY = new Statistics(0L, 0L, 0L);

    private final Long totalMeters;
    private final Long elevationInMeters;
    private final Long totalSeconds;

    public Statistics(final Long totalMeters,
                      final Long elevationInMeters,
                      final Long totalSeconds) {
        this.totalMeters = totalMeters;
        this.elevationInMeters = elevationInMeters;
        this.totalSeconds = totalSeconds;
    }

    public Long getTotalMeters() {
        return totalMeters;
    }

    public Long getElevationInMeters() {
        return elevationInMeters;
    }

    public Long getTotalSeconds() {
        return totalSeconds;
    }

    public Statistics add(final Statistics other) {
        return new Statistics(this.getTotalMeters() + other.getTotalMeters(),
                this.getElevationInMeters() + other.getElevationInMeters(),
                this.getTotalSeconds() + other.getTotalSeconds());
    }

    public static Statistics zero(){
        return new Statistics(0L , 0L, 0L);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(totalMeters, that.totalMeters) &&
                Objects.equals(elevationInMeters, that.elevationInMeters) &&
                Objects.equals(totalSeconds, that.totalSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMeters, elevationInMeters, totalSeconds);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "totalMeters=" + totalMeters +
                ", elevationInMeters=" + elevationInMeters +
                ", totalSeconds=" + totalSeconds +
                '}';
    }
}
