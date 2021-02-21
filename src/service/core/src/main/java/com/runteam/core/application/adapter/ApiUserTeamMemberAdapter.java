package com.runteam.core.application.adapter;

import com.runteam.core.api.*;

public class ApiUserTeamMemberAdapter {

    private final ApiStatusAdapter apiStatusAdapter;
    private final ApiStatisticsAdapter apiStatisticsAdapter;
    private final ApiPersonalBestAdapter apiPersonalBestAdapter;
    private final ApiUserAdapter apiUserAdapter;

    public ApiUserTeamMemberAdapter(final ApiStatusAdapter apiStatusAdapter,
                                    final ApiStatisticsAdapter apiStatisticsAdapter,
                                    final ApiPersonalBestAdapter apiPersonalBestAdapter,
                                    final ApiUserAdapter apiUserAdapter) {
        this.apiStatusAdapter = apiStatusAdapter;
        this.apiStatisticsAdapter = apiStatisticsAdapter;
        this.apiPersonalBestAdapter = apiPersonalBestAdapter;
        this.apiUserAdapter = apiUserAdapter;
    }

    public UserTeamMember adaptFromDomain(final com.runteam.core.domain.model.TeamMember domainTeamMember,
                                          final com.runteam.core.domain.model.User domainUser) {
        return new UserTeamMember()
                .id(domainTeamMember.getId().getId())
                .creationDate(domainTeamMember.getCreationDate().toString())
                .status(this.apiStatusAdapter.adaptFromDomain(domainTeamMember.getStatus()))
                .statistics(this.apiStatisticsAdapter.adaptFromDomain(domainTeamMember.getStatistics()))
                .personalBest(this.apiPersonalBestAdapter.adaptFromDomain(domainTeamMember.getPersonalBest()))
                .user(this.apiUserAdapter.adaptFromDomain(domainUser));
    }
}
