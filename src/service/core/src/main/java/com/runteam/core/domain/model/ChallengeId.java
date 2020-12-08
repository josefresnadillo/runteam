package com.runteam.core.domain.model;

// Value Object

public class ChallengeId extends GenericId {
    public ChallengeId(final String id){
        super(id);
    }

    public static ChallengeId randomChallengeId(){
        return new ChallengeId(generate());
    }
}
