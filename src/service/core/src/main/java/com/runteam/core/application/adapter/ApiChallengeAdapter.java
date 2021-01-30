package com.runteam.core.application.adapter;

import com.runteam.core.api.*;

import java.util.Arrays;

public class ApiChallengeAdapter {

    private final ApiUserAdapter apiUserAdapter;
    private final ApiStatisticsAdapter apiStatisticsAdapter;
    private final ApiStatusAdapter apiStatusAdapter;
    private final ApiPrivacyAdapter apiPrivacyAdapter;

    public ApiChallengeAdapter(final ApiUserAdapter apiUserAdapter,
                               final ApiStatisticsAdapter apiStatisticsAdapter,
                               final ApiStatusAdapter apiStatusAdapter,
                               final ApiPrivacyAdapter apiPrivacyAdapter) {
        this.apiUserAdapter = apiUserAdapter;
        this.apiStatisticsAdapter = apiStatisticsAdapter;
        this.apiStatusAdapter = apiStatusAdapter;
        this.apiPrivacyAdapter = apiPrivacyAdapter;
    }

    public Challenge adaptFromDomain(final com.runteam.core.domain.model.Challenge domainChallenge,
                                final com.runteam.core.domain.model.User domainUser) {
        return new Challenge()
                .id(domainChallenge.getId().getId())
                .owner(this.apiUserAdapter.adaptFromDomain(domainUser))
                .displayName(domainChallenge.getDetails().getDisplayName())
                .details(adaptFromDomain(domainChallenge.getDetails()))
                .creationDate(domainChallenge.getCreationDate().toLocalDate().toString())
                .subscriptionDetails(adaptSubscriptionDetailsFromDomain(domainChallenge))
                .statistics(this.apiStatisticsAdapter.adaptFromDomain(domainChallenge.getStatistics()))
                .status(this.apiStatusAdapter.adaptFromDomain(domainChallenge.getStatus()))
                .privacy(this.apiPrivacyAdapter.adaptFromDomain(domainChallenge.getPrivacy()));
    }

    private ChallengeDetails adaptFromDomain(final com.runteam.core.domain.model.ChallengeDetails domainDetails){
        return new ChallengeDetails()
                .imageUrl(domainDetails.getImageUrl())
                .tags(domainDetails.getTags());

    }

    private ChallengeSubscriptionDetails adaptSubscriptionDetailsFromDomain(final com.runteam.core.domain.model.Challenge domainChallenge){
        return new ChallengeSubscriptionDetails()
                .members((long) domainChallenge.getNumberOfMembers());
    }
}
