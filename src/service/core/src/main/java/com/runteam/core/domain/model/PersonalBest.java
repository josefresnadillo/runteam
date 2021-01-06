package com.runteam.core.domain.model;

// Value Object

import java.util.Objects;

public class PersonalBest {

    private static final Long ONE_KM_IN_METERS = 1000L;
    private static final Long FIVE_KM_IN_METERS = 5000L;
    private static final Long TEN_KM_IN_METERS = 10000L;
    private static final Long HALF_MARATHON_KM_IN_METERS = 21096L;
    private static final Long MARATHON_KM_IN_METERS = 42195L;

    private final Long best1kInSeconds;
    private final Long best5kInSeconds;
    private final Long best10kInSeconds;
    private final Long bestHalfMarathonInSeconds;
    private final Long bestMarathonInSeconds;

    public PersonalBest(final Long best1kInSeconds,
                        final Long best5kInSeconds,
                        final Long best10kInSeconds,
                        final Long bestHalfMarathonInSeconds,
                        final Long bestMarathonInSeconds) {
        this.best1kInSeconds = best1kInSeconds;
        this.best5kInSeconds = best5kInSeconds;
        this.best10kInSeconds = best10kInSeconds;
        this.bestHalfMarathonInSeconds = bestHalfMarathonInSeconds;
        this.bestMarathonInSeconds = bestMarathonInSeconds;
    }

    public Long getBest1kInSeconds() {
        return best1kInSeconds;
    }

    public Long getBest5kInSeconds() {
        return best5kInSeconds;
    }

    public Long getBest10kInSeconds() {
        return best10kInSeconds;
    }

    public Long getBestHalfMarathonInSeconds() {
        return bestHalfMarathonInSeconds;
    }

    public Long getBestMarathonInSeconds() {
        return bestMarathonInSeconds;
    }

    public static PersonalBest zero(){
        return new PersonalBest(0L,
                0L,
                0L,
                0L,
                0L);
    }

    public static PersonalBest create(final Long totalMeters,
                                      final Long totalSeconds) {
        return new PersonalBest(calculate(totalMeters, totalSeconds, ONE_KM_IN_METERS),
                calculate(totalMeters, totalSeconds, FIVE_KM_IN_METERS),
                calculate(totalMeters, totalSeconds, TEN_KM_IN_METERS),
                calculate(totalMeters, totalSeconds, HALF_MARATHON_KM_IN_METERS),
                calculate(totalMeters, totalSeconds, MARATHON_KM_IN_METERS));
    }

    public static PersonalBest mix(final PersonalBest best1,
                                   final PersonalBest best2) {
        return new PersonalBest(calculateBest(best1.getBest1kInSeconds(), best2.getBest1kInSeconds()),
                calculateBest(best1.getBest5kInSeconds(), best2.getBest5kInSeconds()),
                calculateBest(best1.getBest10kInSeconds(), best2.getBest10kInSeconds()),
                calculateBest(best1.getBestHalfMarathonInSeconds(), best2.getBestHalfMarathonInSeconds()),
                calculateBest(best1.getBestMarathonInSeconds(), best2.getBestMarathonInSeconds()));
    }

    private static Long calculate(final Long totalMeters,
                                  final Long totalSeconds,
                                  final Long distanceInMeters) {
        final double paceInMeterSeconds = totalMeters / (double) totalSeconds;
        return (totalMeters >= distanceInMeters) ?
                Double.valueOf(distanceInMeters / paceInMeterSeconds).longValue() :
                0L;
    }

    private static Long calculateBest(final Long value1,
                                      final Long value2) {
        return (value1 > value2) ? value2 : value1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalBest that = (PersonalBest) o;
        return Objects.equals(best1kInSeconds, that.best1kInSeconds) &&
                Objects.equals(best5kInSeconds, that.best5kInSeconds) &&
                Objects.equals(best10kInSeconds, that.best10kInSeconds) &&
                Objects.equals(bestHalfMarathonInSeconds, that.bestHalfMarathonInSeconds) &&
                Objects.equals(bestMarathonInSeconds, that.bestMarathonInSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(best1kInSeconds, best5kInSeconds, best10kInSeconds, bestHalfMarathonInSeconds, bestMarathonInSeconds);
    }

    @Override
    public String toString() {
        return "PersonalBest{" +
                "best1kInSeconds=" + best1kInSeconds +
                ", best5kInSeconds=" + best5kInSeconds +
                ", best10kInSeconds=" + best10kInSeconds +
                ", best21kInSeconds=" + bestHalfMarathonInSeconds +
                ", best42kInSeconds=" + bestMarathonInSeconds +
                '}';
    }
}
