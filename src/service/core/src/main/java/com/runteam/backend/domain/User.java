package com.runteam.backend.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public class User {
    private final UserId id;
    private final String username;
    private final UserDetails userDetails;
    private final UserStatus status;
    private final OffsetDateTime activation;
    private final Statistics statistics;

    public User(final UserId id,
                final String username,
                final UserDetails userDetails,
                final UserStatus status,
                final OffsetDateTime activation,
                final Statistics statistics){
        this.id = id;
        this.username = username;
        this.userDetails = userDetails;
        this.status = status;
        this.activation = activation;
        this.statistics = statistics;
    }

    public UserId getId() {
        return id;
    }

    public String getUsername() {
        return username;
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
                ", status=" + status +
                ", userDetails=" + userDetails +
                ", activation=" + activation +
                ", statistics=" + statistics +
                '}';
    }
}
