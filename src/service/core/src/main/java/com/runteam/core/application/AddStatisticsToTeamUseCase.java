package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiUserTeamMemberAdapter;
import com.runteam.core.application.adapter.DomainStatisticsAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.TeamMemberRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.AddStatisticsToTeamMember;

import java.time.OffsetDateTime;

public class AddStatisticsToTeamUseCase {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final AddStatisticsToTeamMember addStatisticsToTeamMember;
    private final DomainStatisticsAdapter domainStatisticsAdapter;
    private final ApiUserTeamMemberAdapter apiUserTeamMemberAdapter;

    public AddStatisticsToTeamUseCase(final UserRepository userRepository,
                                      final TeamMemberRepository teamMemberRepository,
                                      final AddStatisticsToTeamMember addStatisticsToTeamMember,
                                      final DomainStatisticsAdapter domainStatisticsAdapter,
                                      final ApiUserTeamMemberAdapter apiUserTeamMemberAdapter) {
        this.userRepository = userRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.addStatisticsToTeamMember = addStatisticsToTeamMember;
        this.domainStatisticsAdapter = domainStatisticsAdapter;
        this.apiUserTeamMemberAdapter = apiUserTeamMemberAdapter;
    }

    public com.runteam.core.api.UserTeamMember add(final String teamMemberId,
                                                   final com.runteam.core.api.Statistics userStatistics,
                                                   final OffsetDateTime date) {
        final TeamMember teamMember = this.teamMemberRepository.findById(new TeamMemberId(teamMemberId));
        final User user = this.userRepository.findById(teamMember.getUserId());
        final Statistics statistics = this.domainStatisticsAdapter.adaptFromApi(userStatistics);

        final TeamMember domainTeamMember = addStatisticsToTeamMember.add(teamMember, statistics, date);

        this.teamMemberRepository.save(domainTeamMember);

        return this.apiUserTeamMemberAdapter.adaptFromDomain(domainTeamMember, user);
    }
}
