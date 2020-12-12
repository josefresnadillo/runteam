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

	public static final OffsetDateTime MAX_TEAM_MEMBER_VALID_TO = OffsetDateTime.of(2048,
	                                                                                1,
	                                                                                1,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                ZoneOffset.UTC);

	public static final TeamMember EMPTY = new TeamMember(UserId.EMPTY,
	                                                      MIN_TEAM_MEMBER_VALID_TO,
	                                                      MIN_TEAM_MEMBER_VALID_TO,
	                                                      Statistics.EMPTY) {
		public boolean isEmpty() {
			return true;
		}
	};

	private final UserId userId;
	private final OffsetDateTime activeFrom;
	private final OffsetDateTime activeTo;
	private final Statistics statistics;

	public TeamMember(final UserId userId,
	                  final OffsetDateTime activeFrom,
	                  final OffsetDateTime activeTo,
	                  final Statistics statistics) {
		this.userId = userId;
		this.activeFrom = activeFrom;
		this.activeTo = activeTo;
		this.statistics = statistics;
	}

	public UserId getUserId() {
		return userId;
	}

	public OffsetDateTime getActiveFrom() {
		return activeFrom;
	}

	public OffsetDateTime getActiveTo() {
		return activeTo;
	}

	public Statistics getStatistics() {
		return isActive() ? statistics : new Statistics(0L, 0L, 0L);
	}

	public Statistics addStatistics(final Statistics statistics) {
		return this.statistics.add(statistics);
	}

	public boolean isActive() {
		return this.getActiveTo().isAfter(OffsetDateTime.now());
	}

	public boolean isInactive() {
		return this.getActiveTo().isBefore(OffsetDateTime.now());
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TeamMember that = (TeamMember) o;
		return Objects.equals(userId, that.userId) &&
			Objects.equals(activeFrom, that.activeFrom) &&
			Objects.equals(activeTo, that.activeTo) &&
			Objects.equals(statistics, that.statistics);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, activeFrom, activeTo, statistics);
	}

	@Override
	public String toString() {
		return "TeamMember{" +
			"userId=" + userId +
			", activeFrom=" + activeFrom +
			", activeTo=" + activeTo +
			", statistics=" + statistics +
			'}';
	}
}
