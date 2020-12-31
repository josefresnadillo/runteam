package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

// Entity

public class Team {

	private final TeamId id;
	private final UserId owner;
	private final OffsetDateTime creationDate;
	private final int numberOfMembers;
	private final int numberOfMemberships;
	private TeamDetails details = TeamDetails.builder().build();
	private Status status = Status.INACTIVE;
	private Privacy privacy = Privacy.PUBLIC;
	private Statistics statistics = Statistics.zero();

	public Team(final TeamId id,
	            final UserId owner,
	            final int numberOfMembers,
	            final int numberOfMemberships) {
		this.id = id;
		this.owner = owner;
		this.creationDate = OffsetDateTime.now(ZoneId.of("UTC"));
		this.numberOfMembers = numberOfMembers;
		this.numberOfMemberships = numberOfMemberships;
	}

	public TeamId getId() {
		return id;
	}

	public UserId getOwnerId() {
		return owner;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public int getNumberOfMembers() {
		return this.numberOfMembers;
	}

	public int getNumberOfMemberships() {
		return numberOfMemberships;
	}

	public TeamDetails getDetails() {
		return details;
	}

	public void setDetails(final TeamDetails details) {
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

	public Statistics getStatistics() {
		return statistics;
	}

	public void addStatistics(final Statistics statistics) {
		this.statistics = this.statistics.add(statistics);
	}

	public boolean isPrivate(final UserId userId) {
		return (this.getPrivacy() == Privacy.PRIVATE) && (!this.getOwnerId().equals(userId));
	}

	public TeamMember addMember(final UserId userId,
	                            final Status status) {
		final TeamMember teamMember = new TeamMember(TeamMemberId.randomId());
		teamMember.setUserId(userId);
		teamMember.setTeamId(this.getId());
		teamMember.setStatus(status);
		return teamMember;
	}

	public void checkIsActiveOrThrow(){
		if (getStatus() != Status.ACTIVE) {
			throw new DomainException(DomainExceptionCode.TEAM_IS_NOT_ACTIVE);
		}
	}

	public void checkOwnerOrThrow(final UserId userId){
		if (!getOwnerId().equals(userId)) {
			throw new DomainException(DomainExceptionCode.TEAM_OWNER_IS_NECESSARY);
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
		Team team = (Team) o;
		return Objects.equals(id, team.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Team{" +
			"id=" + id +
			", owner=" + owner +
			", details=" + details +
			", status=" + status +
			", activationDate=" + creationDate +
			'}';
	}
}
