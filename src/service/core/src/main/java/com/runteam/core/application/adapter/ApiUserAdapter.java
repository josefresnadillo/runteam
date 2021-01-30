package com.runteam.core.application.adapter;

import com.runteam.core.api.*;
import java.util.Arrays;

public class ApiUserAdapter {

    private final ApiStatisticsAdapter apiStatisticsAdapter;
    private final ApiStatusAdapter apiStatusAdapter;
    private final ApiPrivacyAdapter apiPrivacyAdapter;

    public ApiUserAdapter(final ApiStatisticsAdapter apiStatisticsAdapter,
                          final ApiStatusAdapter apiStatusAdapter,
                          final ApiPrivacyAdapter apiPrivacyAdapter) {
        this.apiStatisticsAdapter = apiStatisticsAdapter;
        this.apiStatusAdapter = apiStatusAdapter;
        this.apiPrivacyAdapter = apiPrivacyAdapter;
    }

    public User adaptFromDomain(final com.runteam.core.domain.model.User domainUser) {
        return new User()
                .id(domainUser.getId().getId())
                .username(domainUser.getCredentials().getUsername())
                .displayName(domainUser.getDetails().getDisplayName())
                .details(adaptFromDomain(domainUser.getDetails()))
                .creationDate(domainUser.getCreationDate().toLocalDate().toString())
                .subscriptionType(adaptFromDomain(domainUser.getSubscriptionType()))
                .personalBest(adaptFromDomain(domainUser.getPersonalBest()))
                .subscriptionDetails(adaptSubscriptionDetailsFromDomain(domainUser))
                .statistics(this.apiStatisticsAdapter.adaptFromDomain(domainUser.getStatistics()))
                .status(this.apiStatusAdapter.adaptFromDomain(domainUser.getStatus()))
                .privacy(this.apiPrivacyAdapter.adaptFromDomain(domainUser.getPrivacy()));
    }

    private UserDetails adaptFromDomain(final com.runteam.core.domain.model.UserDetails domainDetails){
        return new UserDetails()
                .email(domainDetails.getEmail())
                .imageUrl(domainDetails.getImageUrl())
                .birthday(domainDetails.getBirthday().toString())
                .sex(domainDetails.getSex().name())
                .language(domainDetails.getLanguage())
                .city(domainDetails.getCity())
                .countryCode(domainDetails.getCountryCode());
    }

    private PersonalBest adaptFromDomain(final com.runteam.core.domain.model.PersonalBest domainPersonalBest){
        return new PersonalBest()
                .best1kInSeconds(domainPersonalBest.getBest1kInSeconds())
                .best5kInSeconds(domainPersonalBest.getBest5kInSeconds())
                .best10kInSeconds(domainPersonalBest.getBest10kInSeconds())
                .bestMMInSeconds(domainPersonalBest.getBestHalfMarathonInSeconds())
                .bestMMInSeconds(domainPersonalBest.getBestMarathonInSeconds());
    }

    private UserSubscriptionDetails adaptSubscriptionDetailsFromDomain(final com.runteam.core.domain.model.User domainUser){
        return new UserSubscriptionDetails()
                .challengesCreated((long) domainUser.getNumberOfChallengesCreated())
                .memberships((long) domainUser.getNumberOfMemberShips())
                .teamsCreated((long) domainUser.getNumberOfTeamsCreated());
    }

    private UserSubscriptionType adaptFromDomain(final com.runteam.core.domain.model.UserSubscriptionType domainUserType){
        return Arrays.stream(UserSubscriptionType.values())
                .filter(subscriptionType -> subscriptionType.name().equalsIgnoreCase(domainUserType.name()))
                .findFirst()
                .orElse(UserSubscriptionType.BASIC);
    }
}
