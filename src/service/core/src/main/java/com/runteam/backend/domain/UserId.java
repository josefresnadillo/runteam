package com.runteam.backend.domain;

import java.util.UUID;

// Value Object

public class UserId {
    private final String id;

    public UserId(final String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static UserId generate(){
        return new UserId(UUID.randomUUID().toString());
    }
}
