package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.ChallengeMemberRepository;
import com.runteam.core.domain.repository.TeamMemberRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AddStatisticsToUser {

    private final TeamMemberRepository teamMemberRepository;
    private final ChallengeMemberRepository challengeMemberRepository;

    public AddStatisticsToUser(final TeamMemberRepository teamMemberRepository,
                               final ChallengeMemberRepository challengeMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
        this.challengeMemberRepository = challengeMemberRepository;
    }

    public User add(final User user,
                    final Statistics statistics,
                    final OffsetDateTime date) {

        // Check if manager user is active
        user.checkIsActiveOrThrow();

        // Add statistics to user
        user.addStatistics(statistics);

        // Retrieve user team members
        final List<TeamMember> activeTeamMembers = teamMemberRepository.findByUserId(user.getId()).stream()
                .filter(TeamMember::isActive)
                .filter(teamMember -> teamMember.getCreationDate().isBefore(date))
                .collect(Collectors.toList());

        // Add statistics to team members
        activeTeamMembers.forEach(teamMember -> teamMember.addStatistics(statistics));

        // Get the team ids
        final List<TeamId> teamIds = activeTeamMembers.stream()
                .map(TeamMember::getTeamId)
                .collect(Collectors.toList());

        // Retrieve challenges where user teams belongs to using the team ids
        final List<ChallengeMember> activeChallengeMembers = challengeMemberRepository.findByTeamIds(teamIds).stream()
                .filter(ChallengeMember::isActive)
                .filter(challengeMember -> challengeMember.getCreationDate().isBefore(date))
                .collect(Collectors.toList());

        // Add statistics to challenge members
        activeChallengeMembers.forEach(challengeMember -> challengeMember.addStatistics(statistics));

        // update team members
        activeTeamMembers.forEach(teamMemberRepository::save);

        // update challenge members
        activeChallengeMembers.forEach(challengeMemberRepository::save);

        return user;
    }
}
