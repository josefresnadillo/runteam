package com.runteam.core.domain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// Aggregate

public class Team {

    public static final OffsetDateTime MAX_VALID_TO = OffsetDateTime.of(2048,
            1,
            1,
            0,
            0,
            0,
            0,
            ZoneOffset.UTC);

    private final TeamId id;
    private final UserId owner;
    private TeamDetails details = TeamDetails.EMPTY;
    private TeamStatus status = TeamStatus.INACTIVE;
    private OffsetDateTime activationDate = OffsetDateTime.now();
    private List<TeamMember> members = List.of();
    private List<TeamMember> applicants = List.of();

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

    public void setDetails(TeamDetails details) {
        this.details = details;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public OffsetDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(OffsetDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public List<TeamMember> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<TeamMember> applicants) {
        this.applicants = applicants;
    }

    public Statistics statistics() {
        return this.members.stream().map(TeamMember::getStatistics).reduce(Statistics.EMPTY, Statistics::add);
    }

    public List<TeamMember> getActiveMembers() {
        return this.members.stream()
                .filter(TeamMember::isActive)
                .collect(Collectors.toList());
    }

    public List<TeamMember> getInactiveMembers() {
        return this.members.stream()
                .filter(m -> !m.isActive())
                .collect(Collectors.toList());
    }

    public boolean addMember(final UserId userId) {
        final Optional<TeamMember> applicant = findMemberById(this.applicants, userId);
        if (applicant.isPresent()) {
            this.applicants.remove(applicant.get());
            return this.members.add(new TeamMember(userId,
                    OffsetDateTime.now(),
                    MAX_VALID_TO,
                    Statistics.zero()));
        }
        final Optional<TeamMember> teamMember = findMemberById(this.members, userId);
        if (teamMember.isEmpty()) {
            return this.members.add(new TeamMember(userId,
                    OffsetDateTime.now(),
                    MAX_VALID_TO,
                    Statistics.zero()));
        }
        if (!teamMember.get().isActive()) {
            return this.members.add(new TeamMember(userId,
                    teamMember.get().getActiveFrom(),
                    MAX_VALID_TO,
                    teamMember.get().getStatistics()));
        }
        return false;
    }

    public boolean removeMember(final UserId userId) {
        final Optional<TeamMember> applicant = findMemberById(this.applicants, userId);
        if (applicant.isPresent()) {
            return this.applicants.remove(applicant.get());
        }
        final Optional<TeamMember> member = findMemberById(this.getActiveMembers(), userId);
        if (member.isPresent()) {
            this.members.remove(member.get());
            return this.members.add(new TeamMember(userId,
                    member.get().getActiveFrom(),
                    OffsetDateTime.now(),
                    member.get().getStatistics()));
        }
        return false;
    }

    private Optional<TeamMember> findMemberById(final List<TeamMember> users,
                                                final UserId userId) {
        return users.stream()
                .filter(m -> m.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
