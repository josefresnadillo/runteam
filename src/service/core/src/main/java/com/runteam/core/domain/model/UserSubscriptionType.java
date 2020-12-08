package com.runteam.core.domain.model;

// Value Object

public enum UserSubscriptionType {
    BASIC(3, 3),
    PREMIUM(1000, 1000);

    private final int maxTeams;
    private final int maxChallenges;

    UserSubscriptionType(final int maxTeams,
                         final int maxChallenges){
        this.maxTeams = maxTeams;
        this.maxChallenges = maxChallenges;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public int getMaxChallenges() {
        return maxChallenges;
    }
}
