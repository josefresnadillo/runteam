package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
	private List<TeamMember> members = List.of();
	private List<UserId> applicants = List.of();

	public Team(final TeamId id,
	            final UserId owner) {
		this.id = id;
		this.owner = owner;
	}

	public TeamId getId() {
		return id;
	}

	public UserId getOwner() {
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

	public List<TeamMember> getMembers() {
		return members;
	}

	public void setMembers(final List<TeamMember> members) {
		this.members = members;
	}

	public List<UserId> getApplicants() {
		return applicants;
	}

	public void setApplicants(final List<UserId> applicants) {
		this.applicants = applicants.stream().distinct().collect(Collectors.toList());
	}

	public Statistics statistics() {
		return this.members.stream().map(TeamMember::getStatistics).reduce(Statistics.zero(), Statistics::add);
	}

	public List<TeamMember> getActiveMembers() {
		return this.members.stream()
		                   .filter(TeamMember::isActive)
		                   .collect(Collectors.toList());
	}

	public void addApplicant(final UserId userId) {
		if (!this.applicants.contains(userId)) {
			this.applicants.add(userId);
		}
	}

	public void removeApplicant(final UserId userId) {
		this.applicants.add(userId);
	}

	public void addMember(final UserId userId,
	                      final OffsetDateTime now) {
		removeApplicant(userId);
		final TeamMember teamMember = findTeamMemberById(this.members, userId);
		if (teamMember.isEmpty()) {
			this.members.add(new TeamMember(userId,
			                                now,
			                                TeamMember.MAX_TEAM_MEMBER_VALID_TO,
			                                Statistics.zero()));
			return;
		}
		if (teamMember.isInactive()) {
			updateTeamMember(teamMember,
			                 TeamMember.MAX_TEAM_MEMBER_VALID_TO,
			                 teamMember.getStatistics());
		}
	}

	public void removeMember(final UserId userId,
	                         final OffsetDateTime now) {
		final TeamMember member = findTeamMemberById(this.getActiveMembers(), userId);
		updateTeamMember(member,
		                 now, // set to inactive from now on
		                 member.getStatistics());
	}

	public void addStatistics(final UserId userId,
	                          final Statistics statistics) {
		final TeamMember member = findTeamMemberById(this.getActiveMembers(), userId);
		updateTeamMember(member,
		                 member.getActiveTo(),
		                 member.addStatistics(statistics));
	}

	private void updateTeamMember(final TeamMember teamMember,
	                              final OffsetDateTime activeTo,
	                              final Statistics statistics) {
		if (!teamMember.isEmpty()) {
			this.members.remove(teamMember);
			this.members.add(new TeamMember(teamMember.getUserId(),
			                                teamMember.getActiveFrom(),
			                                activeTo,
			                                statistics));
		}
	}

	private TeamMember findTeamMemberById(final List<TeamMember> users,
	                                      final UserId userId) {
		return users.stream()
		            .filter(m -> m.getUserId().equals(userId))
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
			", applicantMembers=" + applicants +
			'}';
	}
}
