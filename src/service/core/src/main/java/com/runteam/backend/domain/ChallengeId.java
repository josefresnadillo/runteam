package com.runteam.backend.domain;

import java.util.UUID;

// Value Object

public class ChallengeId {
    private final String id;

    public ChallengeId(final String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static ChallengeId generate(){
        return new ChallengeId(UUID.randomUUID().toString());
    }
}
