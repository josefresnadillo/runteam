package com.runteam.core.domain.service;

import com.runteam.core.domain.event.StatisticsTeamMemberEvent;
import com.runteam.core.domain.event.StatisticsUserEvent;
import com.runteam.core.domain.model.ChallengeMember;
import com.runteam.core.domain.model.TeamMember;
import com.runteam.core.domain.repository.ChallengeMemberRepository;
import com.runteam.core.domain.repository.TeamMemberRepository;
import java.util.ArrayList;
import java.util.List;

public class DomainEvents {

    private final AddStatisticsToTeamMember addStatisticsToTeamMember;
    private final AddStatisticsToChallengeMember addStatisticsToChallengeMember;
    private final TeamMemberRepository teamMemberRepository;
    private final ChallengeMemberRepository challengeMemberRepository;

    public DomainEvents(final AddStatisticsToTeamMember addStatisticsToTeamMember,
                        final AddStatisticsToChallengeMember addStatisticsToChallengeMember,
                        final TeamMemberRepository teamMemberRepository,
                        final ChallengeMemberRepository challengeMemberRepository) {
        this.addStatisticsToTeamMember = addStatisticsToTeamMember;
        this.addStatisticsToChallengeMember = addStatisticsToChallengeMember;
        this.teamMemberRepository = teamMemberRepository;
        this.challengeMemberRepository = challengeMemberRepository;
    }

    public void sendEvent(final StatisticsUserEvent event) {
        final List<TeamMember> teamMembers = new ArrayList<>(teamMemberRepository.findByUserId(event.getUserId()));
        teamMembers.forEach(teamMember -> {
            final TeamMember result = this.addStatisticsToTeamMember.add(teamMember, event.getStatistics(), event.getDate());
            teamMemberRepository.save(result);
        });
    }

    public void sendEvent(final StatisticsTeamMemberEvent event) {
        final TeamMember teamMember = teamMemberRepository.findById(event.getTeamMemberId());
        final List<ChallengeMember> activeChallengeMembers = challengeMemberRepository.findByTeamId(teamMember.getTeamId());
        activeChallengeMembers.forEach(challengeMember -> {
            final ChallengeMember result = this.addStatisticsToChallengeMember.add(challengeMember, event.getStatistics(), event.getDate());
            challengeMemberRepository.save(result);
        });
    }
}
