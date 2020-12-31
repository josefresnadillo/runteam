package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

// Aggregate

public class Challenge {

	private final ChallengeId id;
	private final UserId ownerId;
	private final OffsetDateTime creationDate;
	private final int numberOfMembers;
	private ChallengeDetails details = ChallengeDetails.builder().build();
	private Privacy privacy = Privacy.PUBLIC;
	private ChallengeGoal goal = ChallengeGoal.zero();
	private Status status = Status.INACTIVE;
	private Statistics statistics = Statistics.zero();

	public Challenge(final ChallengeId id,
	                 final UserId ownerId,
	                 final int numberOfMembers) {
		this.id = id;
		this.ownerId = ownerId;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
		this.numberOfMembers = numberOfMembers;
	}

	public ChallengeId getId() {
		return id;
	}

	public UserId getOwnerId() {
		return ownerId;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	public ChallengeDetails getDetails() {
		return details;
	}

	public void setDetails(final ChallengeDetails details) {
		this.details = details;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(final Privacy privacy) {
		this.privacy = privacy;
	}

	public ChallengeGoal getGoal() {
		return goal;
	}

	public void setGoal(final ChallengeGoal goal) {
		this.goal = goal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void addStatistics(final Statistics statistics) {
		this.statistics = this.statistics.add(statistics);
	}

	public boolean isPrivate(final UserId userId) {
		return (this.getPrivacy() == Privacy.PRIVATE) && (!this.getOwnerId().equals(userId));
	}

	public ChallengeMember addMember(final TeamId teamId,
	                                 final Status status) {
		ChallengeMember challengeMember = new ChallengeMember(ChallengeMemberId.randomId(), this.getId(), teamId);
		challengeMember.setStatus(status);
		return challengeMember;
	}

	public void checkIsActiveOrThrow(){
		if (getStatus() != Status.ACTIVE) {
			throw new DomainException(DomainExceptionCode.CHALLENGE_IS_NOT_ACTIVE);
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
		Challenge challenge = (Challenge) o;
		return Objects.equals(id, challenge.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Challenge{" +
			"id=" + id +
			", owner=" + ownerId +
			", creationDate=" + creationDate +
			", numberOfTeams=" + numberOfMembers +
			", details=" + details +
			", privacy=" + privacy +
			", goal=" + goal +
			", status=" + status +
			", statistics=" + statistics +
			'}';
	}
}
