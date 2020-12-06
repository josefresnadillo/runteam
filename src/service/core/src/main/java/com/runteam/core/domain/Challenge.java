package com.runteam.core.domain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// Aggregate

public class Challenge {

    public static final OffsetDateTime MAX_VALID_TO = OffsetDateTime.of(2048,
            1,
            1,
            0,
            0,
            0,
            0,
            ZoneOffset.UTC);

    private final ChallengeId id;
    private final UserId owner;
    private ChallengeDetails details;
    private OffsetDateTime activationDate;
    private ChallengeGoal goal;
    private ChallengeStatus status;
    private List<ChallengeMember> members;
    private List<ChallengeMember> applicants;

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

    public void setDetails(ChallengeDetails details) {
        this.details = details;
    }

    public OffsetDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(OffsetDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public ChallengeGoal getGoal() {
        return goal;
    }

    public void setGoal(ChallengeGoal goal) {
        this.goal = goal;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }

    public List<ChallengeMember> getMembers() {
        return members;
    }

    public void setMembers(List<ChallengeMember> members) {
        this.members = members;
    }

    public List<ChallengeMember> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<ChallengeMember> applicants) {
        this.applicants = applicants;
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

    public void addMember(final TeamId teamId) {
        final Optional<ChallengeMember> applicant = findMemberById(this.applicants, teamId);
        if (applicant.isPresent()) {
            this.applicants.remove(applicant.get());
            this.members.add(new ChallengeMember(teamId,
                    OffsetDateTime.now(),
                    MAX_VALID_TO,
                    new Statistics(0L, 0L, 0L)));
            return;
        }
        final Optional<ChallengeMember> challengeMember = findMemberById(this.members, teamId);
        if (challengeMember.isEmpty()) {
            this.members.add(new ChallengeMember(teamId,
                    OffsetDateTime.now(),
                    MAX_VALID_TO,
                    Statistics.zero()));
            return;
        }
        if (!challengeMember.get().isActive()) {
            this.members.add(new ChallengeMember(teamId,
                    challengeMember.get().getActiveFrom(),
                    MAX_VALID_TO,
                    challengeMember.get().getStatistics()));
        }
    }

    public void removeMember(final TeamId teamId) {
        final Optional<ChallengeMember> applicant = findMemberById(this.applicants, teamId);
        if (applicant.isPresent()) {
            this.applicants.remove(applicant.get());
            return;
        }
        final Optional<ChallengeMember> challengeMember = findMemberById(this.getActiveMembers(), teamId);
        if (challengeMember.isPresent()) {
            this.members.remove(challengeMember.get());
            this.members.add(new ChallengeMember(teamId,
                    challengeMember.get().getActiveFrom(),
                    OffsetDateTime.now(),
                    challengeMember.get().getStatistics()));
        }
    }

    public Statistics statistics() {
        return this.members.stream().map(ChallengeMember::getStatistics).reduce(Statistics.EMPTY, Statistics::add);
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
