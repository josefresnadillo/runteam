package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

// Aggregate

public class User {

	private static final OffsetDateTime DEFAULT_ACTIVATION_DATE = OffsetDateTime.of(2048,
	                                                                                1,
	                                                                                1,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                ZoneOffset.UTC);

	private final UserId id;
	private UserCredentials credentials = UserCredentials.EMPTY;
	private UserDetails details = UserDetails.builder().build();
	private Status status = Status.INACTIVE;
	private Privacy privacy = Privacy.PUBLIC;
	private UserSubscriptionType subscriptionType = UserSubscriptionType.BASIC;
	private OffsetDateTime activationDate = DEFAULT_ACTIVATION_DATE;
	private Statistics statistics = Statistics.zero();

	public User(final UserId id) {
		this.id = id;
	}

	public UserId getId() {
		return id;
	}

	public UserCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(final UserCredentials credentials) {
		this.credentials = credentials;
	}

	public UserDetails getDetails() {
		return details;
	}

	public void setDetails(final UserDetails details) {
		this.details = details;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(final Privacy privacy) {
		this.privacy = privacy;
	}

	public UserSubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(final UserSubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public OffsetDateTime getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(final OffsetDateTime activationDate) {
		this.activationDate = activationDate;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(final Statistics statistics) {
		this.statistics = statistics;
	}

	public void addStatistics(final Statistics statistics) {
		this.statistics = this.statistics.add(statistics);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
