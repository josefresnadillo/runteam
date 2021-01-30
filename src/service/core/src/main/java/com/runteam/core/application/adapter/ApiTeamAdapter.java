package com.runteam.core.application.adapter;

import com.runteam.core.api.*;

public class ApiTeamAdapter {
    private final ApiUserAdapter apiUserAdapter;
    private final ApiStatisticsAdapter apiStatisticsAdapter;
    private final ApiStatusAdapter apiStatusAdapter;
    private final ApiPrivacyAdapter apiPrivacyAdapter;

    public ApiTeamAdapter(final ApiUserAdapter apiUserAdapter,
                          final ApiStatisticsAdapter apiStatisticsAdapter,
                          final ApiStatusAdapter apiStatusAdapter,
                          final ApiPrivacyAdapter apiPrivacyAdapter) {
        this.apiUserAdapter = apiUserAdapter;
        this.apiStatisticsAdapter = apiStatisticsAdapter;
        this.apiStatusAdapter = apiStatusAdapter;
        this.apiPrivacyAdapter = apiPrivacyAdapter;
    }

    public Team adaptFromDomain(final com.runteam.core.domain.model.Team domainTeam,
                                final com.runteam.core.domain.model.User domainUser) {
        return new Team()
                .id(domainTeam.getId().getId())
                .owner(apiUserAdapter.adaptFromDomain(domainUser))
                .displayName(domainTeam.getDetails().getDisplayName())
                .details(adaptFromDomain(domainTeam.getDetails()))
                .creationDate(domainTeam.getCreationDate().toLocalDate().toString())
                .subscriptionDetails(adaptSubscriptionDetailsFromDomain(domainTeam))
                .statistics(this.apiStatisticsAdapter.adaptFromDomain(domainTeam.getStatistics()))
                .status(this.apiStatusAdapter.adaptFromDomain(domainTeam.getStatus()))
                .privacy(this.apiPrivacyAdapter.adaptFromDomain(domainTeam.getPrivacy()));
    }

    public TeamDetails adaptFromDomain(final com.runteam.core.domain.model.TeamDetails domainDetails){
        return new TeamDetails()
                .imageUrl(domainDetails.getImageUrl())
                .city(domainDetails.getCity())
                .countryCode(domainDetails.getCountryCode());
    }

    private TeamSubscriptionDetails adaptSubscriptionDetailsFromDomain(final com.runteam.core.domain.model.Team domainTeam){
        return new TeamSubscriptionDetails()
                .members((long) domainTeam.getNumberOfMembers())
                .memberships((long) domainTeam.getNumberOfMemberships());
    }
}
