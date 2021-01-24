package com.runteam.core.application.adapter;

import com.runteam.core.api.*;
import java.util.Arrays;

public class ApiUserAdapter {

    public ApiUserAdapter() {}

    public User adaptFromDomain(final com.runteam.core.domain.model.User domainUser) {
        final User apiUser = new User();

        new User()
                .id(domainUser.getId().getId())
                .username(domainUser.getCredentials().getUsername())
                .displayName(domainUser.getDetails().getDisplayName())
                .details(adapt(domainUser.getDetails()))
                .creationDate(domainUser.getCreationDate().toLocalDate().toString())
                .subscriptionType(adapt(domainUser.getSubscriptionType()))
                .personalBest(adapt(domainUser.getPersonalBest()))
                .subscriptionDetails(adapt(domainUser))
                .statistics(adapt(domainUser.getStatistics()))
                .privacy(adapt(domainUser.getPrivacy()))
                .status(adapt(domainUser.getStatus()));

        return apiUser;
    }

    private UserDetails adapt(final com.runteam.core.domain.model.UserDetails domainDetails){
        return new UserDetails()
                .email(domainDetails.getEmail())
                .imageUrl(domainDetails.getImageUrl())
                .birthday(domainDetails.getBirthday().toString())
                .sex(domainDetails.getSex().name())
                .language(domainDetails.getLanguage())
                .city(domainDetails.getCity())
                .countryCode(domainDetails.getCountryCode());
    }

    private PersonalBest adapt(final com.runteam.core.domain.model.PersonalBest domainPersonalBest){
        return new PersonalBest()
                .best1kInSeconds(domainPersonalBest.getBest1kInSeconds())
                .best5kInSeconds(domainPersonalBest.getBest5kInSeconds())
                .best10kInSeconds(domainPersonalBest.getBest10kInSeconds())
                .bestMMInSeconds(domainPersonalBest.getBestHalfMarathonInSeconds())
                .bestMMInSeconds(domainPersonalBest.getBestMarathonInSeconds());
    }

    private Statistics adapt(final com.runteam.core.domain.model.Statistics domainStatistics){
        return new Statistics()
                .elevation(domainStatistics.getElevationInMeters())
                .meters(domainStatistics.getTotalMeters())
                .seconds(domainStatistics.getTotalSeconds());
    }

    private UserSubscriptionDetails adapt(final com.runteam.core.domain.model.User domainUser){
        return new UserSubscriptionDetails()
                .challengesCreated((long) domainUser.getNumberOfChallengesCreated())
                .memberships((long) domainUser.getNumberOfMemberShips())
                .teamsCreated((long) domainUser.getNumberOfTeamsCreated());
    }

    private Status adapt(final com.runteam.core.domain.model.Status domainStatus){
        return Arrays.stream(Status.values())
                .filter(status -> status.name().equalsIgnoreCase(domainStatus.name()))
                .findFirst()
                .orElse(Status.INACTIVE);
    }

    private UserSubscriptionType adapt(final com.runteam.core.domain.model.UserSubscriptionType domainUserType){
        return Arrays.stream(UserSubscriptionType.values())
                .filter(subscriptionType -> subscriptionType.name().equalsIgnoreCase(domainUserType.name()))
                .findFirst()
                .orElse(UserSubscriptionType.BASIC);
    }

    private Privacy adapt(final com.runteam.core.domain.model.Privacy domainPrivacy){
        return Arrays.stream(Privacy.values())
                .filter(privacy -> privacy.name().equalsIgnoreCase(domainPrivacy.name()))
                .findFirst()
                .orElse(Privacy.PUBLIC);
    }
}
