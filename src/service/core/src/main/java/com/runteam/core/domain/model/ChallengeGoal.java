package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

// Value Object

public class ChallengeGoal {

    public static final ChallengeGoal EMPTY = new ChallengeGoal(0L,
            0L,
            OffsetDateTime.of(170, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC),
            OffsetDateTime.of(170, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));

    private final Long meters;
    private final Long elevationInMeters;
    private final OffsetDateTime activeFrom;
    private final OffsetDateTime activeTo;

    public ChallengeGoal(final Long meters,
                         final Long elevationInMeters,
                         final OffsetDateTime activeFrom,
                         final OffsetDateTime activeTo) {
        this.meters = meters;
        this.elevationInMeters = elevationInMeters;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
    }

    public Long getMeters() {
        return meters;
    }

    public Long getElevationInMeters() {
        return elevationInMeters;
    }

    public OffsetDateTime getActiveFrom() {
        return activeFrom;
    }

    public OffsetDateTime getActiveTo() {
        return activeTo;
    }

    public static ChallengeGoal zero(){
        return new ChallengeGoal(0L,
                0L,
                OffsetDateTime.of(170, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(170, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeGoal that = (ChallengeGoal) o;
        return Objects.equals(meters, that.meters) &&
                Objects.equals(elevationInMeters, that.elevationInMeters) &&
                Objects.equals(activeFrom, that.activeFrom) &&
                Objects.equals(activeTo, that.activeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters, elevationInMeters, activeFrom, activeTo);
    }

    @Override
    public String toString() {
        return "ChallengeGoal{" +
                "meters=" + meters +
                ", elevationInMeters=" + elevationInMeters +
                ", activeFrom=" + activeFrom +
                ", activeTo=" + activeTo +
                '}';
    }
}
