package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.util.Objects;

// Value Object

public class ChallengeGoal {

	public static final ChallengeGoal EMPTY = ChallengeGoal.zero();

	private final Long meters;
	private final Long elevationInMeters;
	private final OffsetDateTime activeFrom;
	private final OffsetDateTime activeTo;

	private ChallengeGoal(final Long meters,
	                     final Long elevationInMeters,
	                     final OffsetDateTime activeFrom,
	                     final OffsetDateTime activeTo) {
		this.meters = meters;
		this.elevationInMeters = elevationInMeters;
		this.activeFrom = activeFrom;
		this.activeTo = activeTo;
	}

	public Long getMeters() {
		return meters;
	}

	public Long getElevationInMeters() {
		return elevationInMeters;
	}

	public OffsetDateTime getActiveFrom() {
		return activeFrom;
	}

	public OffsetDateTime getActiveTo() {
		return activeTo;
	}

	public static ChallengeGoal zero() {
		return new ChallengeGoal(0L,
		                         0L,
		                         OffsetDateTime.MIN,
		                         OffsetDateTime.MAX);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ChallengeGoal that = (ChallengeGoal) o;
		return Objects.equals(meters, that.meters) &&
			Objects.equals(elevationInMeters, that.elevationInMeters) &&
			Objects.equals(activeFrom, that.activeFrom) &&
			Objects.equals(activeTo, that.activeTo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(meters, elevationInMeters, activeFrom, activeTo);
	}

	@Override
	public String toString() {
		return "ChallengeGoal{" +
			"meters=" + meters +
			", elevationInMeters=" + elevationInMeters +
			", activeFrom=" + activeFrom +
			", activeTo=" + activeTo +
			'}';
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Long meters = 0L;
		private Long elevationInMeters = 0L;
		private OffsetDateTime activeFrom = OffsetDateTime.MIN;
		private OffsetDateTime activeTo = OffsetDateTime.MAX;

		public Builder() {
		}

		public Builder meters(final Long value) {
			this.meters = value;
			return this;
		}

		public Builder elevationInMeters(final Long value) {
			this.elevationInMeters = value;
			return this;
		}

		public Builder activeFrom(final OffsetDateTime value) {
			this.activeFrom = value;
			return this;
		}

		public Builder activeTo(final OffsetDateTime value) {
			this.activeTo = value;
			return this;
		}

		public ChallengeGoal build() {
			return new ChallengeGoal(this.meters,
					this.elevationInMeters,
					this.activeFrom,
					this.activeTo);
		}
	}
}
