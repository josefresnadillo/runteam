package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AddStatisticsToUser {

    private static final Logger LOGGER = Logger.getLogger(AddStatisticsToUser.class.toString());

    private final ChallengeRepository challengeRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final ChallengeMemberRepository challengeMemberRepository;

    public AddStatisticsToUser(final ChallengeRepository challengeRepository,
                               final TeamMemberRepository teamMemberRepository,
                               final ChallengeMemberRepository challengeMemberRepository) {
        this.challengeRepository = challengeRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.challengeMemberRepository = challengeMemberRepository;
    }

    public User add(final User user,
                    final Statistics statistics) {

        // Check if manager user is active
        user.checkIsActiveOrThrow();

        // Add statistics to user
        user.addStatistics(statistics);

        // Retrieve user team members
        final List<TeamMember> activeTeamMembers = teamMemberRepository.findByUserId(user.getId()).stream()
                .filter(TeamMember::isActive)
                .collect(Collectors.toList());

        // Add statistics to team members
        activeTeamMembers.forEach(teamMember -> teamMember.addStatistics(statistics));

        // Retrieve challenges where user teams belongs to
        final List<TeamId> teamIds = activeTeamMembers.stream()
                .map(TeamMember::getTeamId)
                .collect(Collectors.toList());
        final List<ChallengeMember> activeChallengeMembers = challengeMemberRepository.findByTeamIds(teamIds).stream()
                .filter(ChallengeMember::isActive)
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
