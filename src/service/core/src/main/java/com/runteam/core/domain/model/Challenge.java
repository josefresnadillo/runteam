package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Aggregate

public class Challenge {

	private static final OffsetDateTime DEFAULT_ACTIVATION_DATE = OffsetDateTime.of(2048,
	                                                                                1,
	                                                                                1,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                0,
	                                                                                ZoneOffset.UTC);

	private final ChallengeId id;
	private final UserId owner;
	private ChallengeDetails details = ChallengeDetails.builder().build();
	private Privacy privacy = Privacy.PUBLIC;
	private OffsetDateTime activationDate = DEFAULT_ACTIVATION_DATE;
	private ChallengeGoal goal = ChallengeGoal.zero();
	private ChallengeStatus status = ChallengeStatus.INACTIVE;
	private List<ChallengeMember> members = List.of();
	private List<TeamId> applicants = List.of();

	public Challenge(final ChallengeId id,
	                 final UserId owner) {
		this.id = id;
		this.owner = owner;
	}

	public ChallengeId getId() {
		return id;
	}

	public UserId getOwner() {
		return owner;
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

	public OffsetDateTime getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(final OffsetDateTime activationDate) {
		this.activationDate = activationDate;
	}

	public ChallengeGoal getGoal() {
		return goal;
	}

	public void setGoal(final ChallengeGoal goal) {
		this.goal = goal;
	}

	public ChallengeStatus getStatus() {
		return status;
	}

	public void setStatus(final ChallengeStatus status) {
		this.status = status;
	}

	public List<ChallengeMember> getMembers() {
		return members;
	}

	public void setMembers(final List<ChallengeMember> members) {
		this.members = members;
	}

	public List<TeamId> getApplicants() {
		return applicants;
	}

	public void setApplicants(final List<TeamId> applicants) {
		this.applicants = applicants.stream().distinct().collect(Collectors.toList());
	}

	public Statistics statistics() {
		return this.members.stream().map(ChallengeMember::getStatistics).reduce(Statistics.EMPTY, Statistics::add);
	}

	public List<ChallengeMember> getActiveMembers() {
		return this.members.stream()
		                   .filter(ChallengeMember::isActive)
		                   .collect(Collectors.toList());
	}

	public List<ChallengeMember> getInactiveMembers() {
		return this.members.stream()
		                   .filter(t -> !t.isActive())
		                   .collect(Collectors.toList());
	}

	public void addApplicant(final TeamId teamId) {
		if (!this.applicants.contains(teamId)) {
			this.applicants.add(teamId);
		}
	}

	public void removeApplicant(final TeamId teamId) {
		this.applicants.add(teamId);
	}

	public void addMember(final TeamId teamId,
	                      final OffsetDateTime now) {
		removeApplicant(teamId);
		final ChallengeMember challengeMember = findChallengeMemberById(this.members, teamId);
		if (challengeMember.isEmpty()) {
			this.members.add(new ChallengeMember(teamId,
			                                     now,
			                                     ChallengeMember.MAX_TEAM_MEMBER_VALID_TO,
			                                     Statistics.zero()));
			return;
		}
		if (challengeMember.isInactive()) {
			addChallengeMember(challengeMember,
			                   ChallengeMember.MAX_TEAM_MEMBER_VALID_TO, // to activate again
			                   challengeMember.getStatistics());
		}
	}

	public void removeMember(final TeamId teamId,
	                         final OffsetDateTime now) {
		final ChallengeMember challengeMember = findChallengeMemberById(this.getActiveMembers(), teamId);
		addChallengeMember(challengeMember,
		                   now,
		                   challengeMember.getStatistics());
	}

	public void addStatistics(final TeamId teamId,
	                          final Statistics statistics) {
		final ChallengeMember member = findChallengeMemberById(this.getActiveMembers(), teamId);
		addChallengeMember(member,
		                   member.getActiveTo(),
		                   member.addStatistics(statistics));
	}

	private void addChallengeMember(final ChallengeMember challengeMember,
	                                final OffsetDateTime activeTo,
	                                final Statistics statistics) {
		if (!challengeMember.isEmpty()) {
			this.members.remove(challengeMember);
			this.members.add(new ChallengeMember(challengeMember.getTeamId(),
			                                     challengeMember.getActiveFrom(),
			                                     activeTo,
			                                     statistics));
		}
	}

	private ChallengeMember findChallengeMemberById(final List<ChallengeMember> teams,
	                                                final TeamId teamId) {
		return teams.stream()
		            .filter(t -> t.getTeamId().equals(teamId))
		            .findFirst()
		            .orElse(ChallengeMember.EMPTY);
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
			", owner=" + owner +
			", details=" + details +
			", activationDate=" + activationDate +
			", goal=" + goal +
			", status=" + status +
			", activeTeams=" + members +
			", applicantTeams=" + applicants +
			'}';
	}
}
