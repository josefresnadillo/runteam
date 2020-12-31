package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

// Entity

public class ChallengeMember {

	public static final ChallengeMember EMPTY = new ChallengeMember(ChallengeMemberId.EMPTY) {
		public boolean isEmpty() {
			return true;
		}
	};

	private final ChallengeMemberId id;
	private final OffsetDateTime creationDate;
	private ChallengeId challengeId;
	private TeamId teamId;
	private Status status = Status.INACTIVE;
	private Statistics statistics = Statistics.zero();

	public ChallengeMember(final ChallengeMemberId id) {
		this.id = id;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
	}

	public ChallengeMember(final ChallengeMemberId id,
	                       final ChallengeId challengeId,
	                       final TeamId teamId) {
		this.id = id;
		this.challengeId = challengeId;
		this.teamId = teamId;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
	}

	public ChallengeMemberId getId() {
		return id;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public ChallengeId getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(final ChallengeId challengeId) {
		this.challengeId = challengeId;
	}

	public TeamId getTeamId() {
		return teamId;
	}

	public void setTeamId(final TeamId teamId) {
		this.teamId = teamId;
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

	public boolean isActive() {
		return this.status == Status.ACTIVE;
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ChallengeMember that = (ChallengeMember) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ChallengeMember{" +
			"id=" + id +
			", creationDate=" + creationDate +
			", challengeId=" + challengeId +
			", teamId=" + teamId +
			", status=" + status +
			", statistics=" + statistics +
			'}';
	}
}
