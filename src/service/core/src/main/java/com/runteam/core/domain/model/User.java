package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

// Entity

public class User {

	private final UserId id;
	private final OffsetDateTime creationDate;
	private final int numberOfTeamsCreated;
	private final int numberOfChallengesCreated;
	private final int numberOfMemberShips;
	private UserCredentials credentials = UserCredentials.EMPTY;
	private UserDetails details = UserDetails.builder().build();
	private Status status = Status.INACTIVE;
	private Privacy privacy = Privacy.PUBLIC;
	private UserSubscriptionType subscriptionType = UserSubscriptionType.BASIC;
	private PersonalBest personalBest = PersonalBest.max();
	private Statistics statistics = Statistics.zero();

	public User(final UserId id,
	            final int numberOfMemberShips,
	            final int numberOfTeamsCreated,
	            final int numberOfChallengesCreated) {
		this.id = id;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
		this.numberOfMemberShips = numberOfMemberShips;
		this.numberOfTeamsCreated = numberOfTeamsCreated;
		this.numberOfChallengesCreated = numberOfChallengesCreated;
	}

	public UserId getId() {
		return id;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public int getNumberOfMemberShips() {
		return numberOfMemberShips;
	}

	public int getNumberOfTeamsCreated() {
		return numberOfTeamsCreated;
	}

	public int getNumberOfChallengesCreated() {
		return numberOfChallengesCreated;
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

	public PersonalBest getPersonalBest() {
		return personalBest;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void addStatistics(final Statistics statistics) {
		this.statistics = this.statistics.add(statistics);
		final PersonalBest best = PersonalBest.create(statistics.getTotalMeters(), statistics.getTotalSeconds());
		this.personalBest = PersonalBest.mix(this.personalBest, best);
	}

	public void checkIsActiveOrThrow(){
		if (getStatus() != Status.ACTIVE) {
			throw new DomainException(DomainExceptionCode.USER_IS_NOT_ACTIVE);
		}
	}

	public void checkNumberOfTeamsCreatedOrThrow(){
		if (getNumberOfTeamsCreated() >= getSubscriptionType().getMaxTeams()) {
			throw new DomainException(DomainExceptionCode.USER_CREATED_TOO_MANY_TEAMS);
		}
	}

	public void checkNumberOfChallengesCreatedOrThrow(){
		if (getNumberOfChallengesCreated() >= getSubscriptionType().getMaxChallenges()) {
			throw new DomainException(DomainExceptionCode.USER_CREATED_TOO_MANY_CHALLENGES);
		}
	}

	public void checkNumberOfMembershipsOrThrow(){
		if (getNumberOfMemberShips() >= getSubscriptionType().getMaxTeamsUserCanBelong()) {
			throw new DomainException(DomainExceptionCode.USER_IN_TOO_MANY_TEAMS);
		}
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
			", numberOfTeams=" + numberOfTeamsCreated +
			", numberOfChallenges=" + numberOfChallengesCreated +
			", numberOfTeamMembersships=" + numberOfMemberShips +
			", credentials=" + credentials +
			", details=" + details +
			", status=" + status +
			", privacy=" + privacy +
			", subscriptionType=" + subscriptionType +
			", activationDate=" + creationDate +
			", statistics=" + statistics +
			'}';
	}
}
