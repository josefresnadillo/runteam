package com.runteam.core.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

// Aggregate

public class User {
    private final UserId id;
    private UserCredentials credentials = UserCredentials.EMPTY;
    private UserDetails details = UserDetails.EMPTY;
    private UserStatus status = UserStatus.INACTIVE;
    private UserPrivacy privacy = UserPrivacy.PUBLIC;
    private OffsetDateTime activationDate = OffsetDateTime.now();
    private Statistics statistics = Statistics.EMPTY;

    public User(final UserId id) {
        this.id = id;
    }

    public UserId getId() {
        return id;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserPrivacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(UserPrivacy privacy) {
        this.privacy = privacy;
    }

    public OffsetDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(OffsetDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
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
                ", credentials=" + credentials +
                ", details=" + details +
                ", status=" + status +
                ", privacy=" + privacy +
                ", activationDate=" + activationDate +
                ", statistics=" + statistics +
                '}';
    }
}
