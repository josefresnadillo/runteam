package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

// Value Object

public class TeamMember {

	public static final OffsetDateTime MIN_TEAM_MEMBER_VALID_TO = OffsetDateTime.of(1970,
	                                                                                1,
	                                                                                1,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                ZoneOffset.UTC);

	public static final TeamMember EMPTY = new TeamMember(UserId.EMPTY,
	                                                      MIN_TEAM_MEMBER_VALID_TO,
	                                                      Status.INACTIVE,
	                                                      Statistics.EMPTY) {
		public boolean isEmpty() {
			return true;
		}
	};

	private final UserId id;
	private final OffsetDateTime activeFrom;
	private final Status status;
	private final Statistics statistics;

	public TeamMember(final UserId id,
	                  final OffsetDateTime activeFrom,
	                  final Status status,
	                  final Statistics statistics) {
		this.id = id;
		this.activeFrom = activeFrom;
		this.status = status;
		this.statistics = statistics;
	}

	public UserId getId() {
		return id;
	}

	public OffsetDateTime getActiveFrom() {
		return activeFrom;
	}

	public Status getStatus() {
		return status;
	}

	public Statistics getStatistics() {
		return isActive() ? statistics : new Statistics(0L, 0L, 0L);
	}

	public Statistics addStatistics(final Statistics statistics) {
		return this.statistics.add(statistics);
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
			", activeFrom=" + activeFrom +
			", status=" + status +
			", statistics=" + statistics +
			'}';
	}
}
