package com.runteam.backend.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public class ChallengeGoal {
    private final Long meters;
    private final OffsetDateTime activeTo;

    public ChallengeGoal(final Long meters,
                         final OffsetDateTime activeTo){
        this.meters = meters;
        this.activeTo = activeTo;
    }

    public Long getMeters() {
        return meters;
    }

    public OffsetDateTime getActiveTo() {
        return activeTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeGoal that = (ChallengeGoal) o;
        return Objects.equals(meters, that.meters) &&
                Objects.equals(activeTo, that.activeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters, activeTo);
    }

    @Override
    public String toString() {
        return "ChallengeGoal{" +
                "meters=" + meters +
                ", activeTo=" + activeTo +
                '}';
    }
}
