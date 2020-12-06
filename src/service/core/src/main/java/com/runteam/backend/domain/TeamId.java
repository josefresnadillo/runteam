package com.runteam.backend.domain;

import java.util.UUID;

// Value Object

public class TeamId {
    private final String id;

    public TeamId(final String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static TeamId generate(){
        return new TeamId(UUID.randomUUID().toString());
    }
}
