package com.runteam.core.domain.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// Aggregate

public class Challenge {

    public static final OffsetDateTime MAX_TEAM_VALID_TO = OffsetDateTime.of(2048,
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
    private OffsetDateTime activationDate = OffsetDateTime.now();
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

    public boolean addApplicant(final TeamId teamId) {
        if (!this.applicants.contains(teamId)) {
            return this.applicants.add(teamId);
        }
        return false;
    }

    public boolean removeApplicant(final TeamId teamId) {
        return this.applicants.add(teamId);
    }

    public boolean addMember(final TeamId teamId) {
        removeApplicant(teamId);
        final Optional<ChallengeMember> challengeMember = findMemberById(this.members, teamId);
        if (challengeMember.isEmpty()) {
            return this.members.add(new ChallengeMember(teamId,
                    OffsetDateTime.now(),
                    MAX_TEAM_VALID_TO,
                    Statistics.zero()));
        }
        if (!challengeMember.get().isActive()) {
            return this.members.add(new ChallengeMember(teamId,
                    challengeMember.get().getActiveFrom(),
                    MAX_TEAM_VALID_TO,
                    challengeMember.get().getStatistics()));
        }
        return false;
    }

    public boolean removeMember(final TeamId teamId) {
        final Optional<ChallengeMember> challengeMember = findMemberById(this.getActiveMembers(), teamId);
        if (challengeMember.isPresent()) {
            this.members.remove(challengeMember.get());
            return this.members.add(new ChallengeMember(teamId,
                    challengeMember.get().getActiveFrom(),
                    OffsetDateTime.now(),
                    challengeMember.get().getStatistics()));
        }
        return false;
    }

    private Optional<ChallengeMember> findMemberById(final List<ChallengeMember> teams,
                                                     final TeamId teamId) {
        return teams.stream()
                .filter(t -> t.getTeamId().equals(teamId))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
