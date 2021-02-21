package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiUserAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.UserRepository;

import java.time.LocalDate;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final ApiUserAdapter apiUserAdapter;

    public CreateUserUseCase(final UserRepository userRepository,
                             final ApiUserAdapter apiUserAdapter) {
        this.userRepository = userRepository;
        this.apiUserAdapter = apiUserAdapter;
    }

    public com.runteam.core.api.User createActive(final com.runteam.core.api.User newUser) {
        final User domainUser = com.runteam.core.domain.model.UserFactory.createActive();

        final com.runteam.core.domain.model.UserDetails domainDetails = UserDetails.builder()
                .displayName(newUser.getDisplayName())
                .email(newUser.getDetails().getEmail())
                .imageUrl(newUser.getDetails().getImageUrl())
                .birthday(LocalDate.parse(newUser.getDetails().getBirthday()))
                .sex(UserSex.adapt(newUser.getDetails().getSex()))
                .language(newUser.getDetails().getLanguage())
                .city(newUser.getDetails().getCity())
                .countryCode(newUser.getDetails().getCountryCode())
                .language(newUser.getDetails().getLanguage())
                .build();

        domainUser.setDetails(domainDetails);
        domainUser.setSubscriptionType(UserSubscriptionType.adapt(newUser.getSubscriptionType().name()));
        domainUser.setPrivacy(Privacy.adapt(newUser.getPrivacy().name()));

        // login is always through Strava. So, no secret is needed
        domainUser.setCredentials(new UserCredentials(newUser.getUsername(), ""));

        this.userRepository.save(domainUser);

        return this.apiUserAdapter.adaptFromDomain(domainUser);
    }
}
