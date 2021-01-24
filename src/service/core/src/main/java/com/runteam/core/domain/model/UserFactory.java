package com.runteam.core.domain.model;

// Entity

public class UserFactory {

    public static User createActive() {
        final User newUser = new User(UserId.randomId(), 0, 0, 0);
        newUser.setStatus(Status.ACTIVE);
        return newUser;
    }
}
