package com.runteam.core.application.adapter;

import com.runteam.core.api.ChallengeTeamMember;

public class ApiChallengeTeamMemberAdapter {

    private final ApiStatusAdapter apiStatusAdapter;
    private final ApiStatisticsAdapter apiStatisticsAdapter;
    private final ApiTeamAdapter apiTeamAdapter;

    public ApiChallengeTeamMemberAdapter(final ApiStatusAdapter apiStatusAdapter,
                                         final ApiStatisticsAdapter apiStatisticsAdapter,
                                         final ApiTeamAdapter apiTeamAdapter) {
        this.apiStatusAdapter = apiStatusAdapter;
        this.apiStatisticsAdapter = apiStatisticsAdapter;
        this.apiTeamAdapter = apiTeamAdapter;
    }

    public ChallengeTeamMember adaptFromDomain(final com.runteam.core.domain.model.ChallengeMember domainChallengeMember,
                                               final com.runteam.core.domain.model.Team domainTeam,
                                               final com.runteam.core.domain.model.User domainUser) {
        return new ChallengeTeamMember()
                .id(domainChallengeMember.getId().getId())
                .team(this.apiTeamAdapter.adaptFromDomain(domainTeam, domainUser))
                .creationDate(domainChallengeMember.getCreationDate().toString())
                .statistics(this.apiStatisticsAdapter.adaptFromDomain(domainChallengeMember.getStatistics()))
                .status(this.apiStatusAdapter.adaptFromDomain(domainChallengeMember.getStatus()));
    }
}
