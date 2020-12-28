package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Aggregate

public class Team {

	private static final OffsetDateTime DEFAULT_ACTIVATION_DATE = OffsetDateTime.of(2048,
	                                                                                1,
	                                                                                1,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                ZoneOffset.UTC);
	private final TeamId id;
	private final UserId owner;
	private TeamDetails details = TeamDetails.builder().build();
	private TeamStatus status = TeamStatus.INACTIVE;
	private Privacy privacy = Privacy.PUBLIC;
	private OffsetDateTime activationDate = DEFAULT_ACTIVATION_DATE;
	private final List<TeamMember> members = new ArrayList<>();

	public Team(final TeamId id,
	            final UserId owner) {
		this.id = id;
		this.owner = owner;
	}

	public TeamId getId() {
		return id;
	}

	public UserId getOwnerId() {
		return owner;
	}

	public TeamDetails getDetails() {
		return details;
	}

	public void setDetails(final TeamDetails details) {
		this.details = details;
	}

	public TeamStatus getStatus() {
		return status;
	}

	public void setStatus(final TeamStatus status) {
		this.status = status;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(final Privacy privacy) {
		this.privacy = privacy;
	}

	public OffsetDateTime getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(final OffsetDateTime activationDate) {
		this.activationDate = activationDate;
	}

	public Statistics statistics() {
		return this.members.stream().map(TeamMember::getStatistics).reduce(Statistics.zero(), Statistics::add);
	}

	public List<TeamMember> getActiveMembers() {
		return this.members.stream()
		                   .filter(TeamMember::isActive)
		                   .collect(Collectors.toList());
	}

	public List<TeamMember> getInactiveMembers() {
		return this.members.stream()
		                   .filter(teamMember -> teamMember.getStatus() == Status.INACTIVE)
		                   .collect(Collectors.toList());
	}

	public List<TeamMember> getPendingMembers() {
		return this.members.stream()
		                   .filter(teamMember -> teamMember.getStatus() == Status.PENDING)
		                   .collect(Collectors.toList());
	}

	public void addActiveMember(final UserId userId,
	                            final OffsetDateTime now) {
		final TeamMember teamMember = findTeamMemberById(this.members, userId);
		if (teamMember.isEmpty()) {
			this.members.add(new TeamMember(userId,
			                                now,
			                                Status.ACTIVE,
			                                Statistics.zero()));
		} else {
			updateTeamMember(teamMember,
			                 Status.ACTIVE,
			                 now,
			                 teamMember.getStatistics());
		}
	}

	public void addPendingMember(final UserId userId,
	                             final OffsetDateTime now) {
		final TeamMember teamMember = new TeamMember(userId,
		                                             now,
		                                             Status.PENDING,
		                                             Statistics.zero());
		this.members.remove(teamMember);
		this.members.add(teamMember);
	}

	public void removeMember(final UserId userId) {
		final TeamMember member = findTeamMemberById(this.getActiveMembers(), userId);
		updateTeamMember(member,
		                 Status.INACTIVE,
		                 member.getActiveFrom(),
		                 member.getStatistics());
	}

	public void addStatistics(final UserId userId,
	                          final Statistics statistics) {
		final TeamMember member = findTeamMemberById(this.getActiveMembers(), userId);
		updateTeamMember(member,
		                 member.getStatus(),
		                 member.getActiveFrom(),
		                 member.addStatistics(statistics));
	}

	private void updateTeamMember(final TeamMember teamMember,
	                              final Status status,
	                              final OffsetDateTime now,
	                              final Statistics statistics) {
		if (!teamMember.isEmpty()) {
			this.members.remove(teamMember);
			this.members.add(new TeamMember(teamMember.getId(),
			                                now,
			                                status,
			                                statistics));
		}
	}

	private TeamMember findTeamMemberById(final List<TeamMember> users,
	                                      final UserId userId) {
		return users.stream()
		            .filter(m -> m.getId().equals(userId))
		            .findFirst()
		            .orElse(TeamMember.EMPTY);
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
			", activationDate=" + activationDate +
			", activeMembers=" + members +
			'}';
	}
}
