package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiChallengeTeamMemberAdapter;
import com.runteam.core.application.adapter.DomainStatisticsAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.ChallengeMemberRepository;
import com.runteam.core.domain.repository.TeamRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.AddStatisticsToChallengeMember;

import java.time.OffsetDateTime;

public class AddStatisticsToChallengeUseCase {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ChallengeMemberRepository challengeMemberRepository;
    private final AddStatisticsToChallengeMember addStatisticsToChallengeMember;
    private final DomainStatisticsAdapter domainStatisticsAdapter;
    private final ApiChallengeTeamMemberAdapter apiChallengeTeamMemberAdapter;

    public AddStatisticsToChallengeUseCase(final UserRepository userRepository,
                                           final TeamRepository teamRepository,
                                           final ChallengeMemberRepository challengeMemberRepository,
                                           final AddStatisticsToChallengeMember addStatisticsToChallengeMember,
                                           final DomainStatisticsAdapter domainStatisticsAdapter,
                                           final ApiChallengeTeamMemberAdapter apiChallengeTeamMemberAdapter) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.challengeMemberRepository = challengeMemberRepository;
        this.addStatisticsToChallengeMember = addStatisticsToChallengeMember;
        this.domainStatisticsAdapter = domainStatisticsAdapter;
        this.apiChallengeTeamMemberAdapter = apiChallengeTeamMemberAdapter;
    }

    public com.runteam.core.api.ChallengeTeamMember add(final String challengeMemberId,
                                                   final com.runteam.core.api.Statistics userStatistics,
                                                   final OffsetDateTime date) {

        final ChallengeMember challengeMember = this.challengeMemberRepository.findById(new ChallengeMemberId(challengeMemberId));
        final Team team = this.teamRepository.findById(challengeMember.getTeamId());
        final User user = this.userRepository.findById(team.getOwnerId());
        final Statistics statistics = this.domainStatisticsAdapter.adaptFromApi(userStatistics);

        final ChallengeMember domainChallengeMember = addStatisticsToChallengeMember.add(challengeMember, statistics, date);

        this.challengeMemberRepository.save(domainChallengeMember);

        return this.apiChallengeTeamMemberAdapter.adaptFromDomain(domainChallengeMember, team, user);
    }
}
