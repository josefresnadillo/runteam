package com.runteam.core.application;

import com.runteam.core.api.User;
import com.runteam.core.application.adapter.ApiUserAdapter;
import com.runteam.core.application.adapter.DomainUserDetailsAdapter;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.UserCredentials;
import com.runteam.core.domain.model.UserSubscriptionType;
import com.runteam.core.domain.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private final DomainUserDetailsAdapter domainUserDetailsAdapter;
    private final ApiUserAdapter apiUserAdapter;

    public UserService(final UserRepository userRepository,
                       final DomainUserDetailsAdapter domainUserDetailsAdapter,
                       final ApiUserAdapter apiUserAdapter) {
        this.userRepository = userRepository;
        this.domainUserDetailsAdapter = domainUserDetailsAdapter;
        this.apiUserAdapter = apiUserAdapter;
    }

    public User createUser(final User newUser) {
        com.runteam.core.domain.model.User domainUser = com.runteam.core.domain.model.UserFactory.createActive();

        domainUser.setDetails(this.domainUserDetailsAdapter.adaptFromApi(newUser.getDisplayName(), newUser.getDetails()));
        domainUser.setCredentials(new UserCredentials(newUser.getUsername(), ""));
        domainUser.setSubscriptionType(UserSubscriptionType.adapt(newUser.getSubscriptionType().name()));
        domainUser.setPrivacy(Privacy.adapt(newUser.getPrivacy().name()));

        userRepository.save(domainUser);

        return this.apiUserAdapter.adaptFromDomain(domainUser);
    }
}
