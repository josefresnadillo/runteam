package com.runteam.backend.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public class User {
    private final UserId id;
    private final String username;
    private final String token;
    private final UserStatus status;
    private final UserDetails userDetails;
    private final OffsetDateTime activation;
    private final Statistics statistics;

    public User(final UserId id,
                final String username,
                final String token,
                final UserStatus status,
                final UserDetails userDetails,
                final OffsetDateTime activation,
                final Statistics statistics){
        this.id = id;
        this.username = username;
        this.token = token;
        this.status = status;
        this.userDetails = userDetails;
        this.activation = activation;
        this.statistics = statistics;
    }

    public UserId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public UserStatus getStatus() {
        return status;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public OffsetDateTime getActivation() {
        return activation;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", userDetails=" + userDetails +
                ", activation=" + activation +
                ", statistics=" + statistics +
                '}';
    }
}
