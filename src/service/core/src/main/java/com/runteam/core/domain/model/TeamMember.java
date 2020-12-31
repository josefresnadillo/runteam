package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

// Entity

public class TeamMember {

	public static final TeamMember EMPTY = new TeamMember(TeamMemberId.EMPTY) {
		public boolean isEmpty() {
			return true;
		}
	};

	private final TeamMemberId id;
	private final OffsetDateTime creationDate;
	private TeamId teamId = TeamId.EMPTY;
	private UserId userId = UserId.EMPTY;
	private Status status = Status.INACTIVE;
	private Statistics statistics = Statistics.zero();

	public TeamMember(final TeamMemberId id) {
		this.id = id;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
	}

	public TeamMember(final TeamMemberId id,
	                  final TeamId teamId,
	                  final UserId userId) {
		this.id = id;
		this.teamId = teamId;
		this.userId = userId;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
	}

	public TeamMemberId getId() {
		return id;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public TeamId getTeamId() {
		return teamId;
	}

	public void setTeamId(final TeamId teamId) {
		this.teamId = teamId;
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(final UserId userId) {
		this.userId = userId;
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
		final TeamMember that = (TeamMember) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "TeamMember{" +
			"id=" + id +
			", creationDate=" + creationDate +
			", teamId=" + teamId +
			", userId=" + userId +
			", status=" + status +
			", statistics=" + statistics +
			'}';
	}
}
