package com.runteam.core.domain.model;

// Value Object

import java.util.Objects;

public class UserCredentials {

    public static final UserCredentials EMPTY = new UserCredentials("", "");

    private final String username; // Example @josefresna
    private final String secret;

    public UserCredentials(final String username,
                           final String secret) {
        this.username = username;
        this.secret = secret;
    }

    public String getUsername() {
        return username;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(username, that.username) && Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, secret);
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
