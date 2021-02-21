package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiUserAdapter;
import com.runteam.core.application.adapter.DomainStatisticsAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.AddStatisticsToUser;

import java.time.OffsetDateTime;

public class AddStatisticsToUserUseCase {

    private final UserRepository userRepository;
    private final AddStatisticsToUser addStatisticsToUser;
    private final DomainStatisticsAdapter domainStatisticsAdapter;
    private final ApiUserAdapter apiUserAdapter;

    public AddStatisticsToUserUseCase(final UserRepository userRepository,
                                      final AddStatisticsToUser addStatisticsToUser,
                                      final DomainStatisticsAdapter domainStatisticsAdapter,
                                      final ApiUserAdapter apiUserAdapter) {
        this.userRepository = userRepository;
        this.addStatisticsToUser = addStatisticsToUser;
        this.domainStatisticsAdapter = domainStatisticsAdapter;
        this.apiUserAdapter = apiUserAdapter;
    }

    public com.runteam.core.api.User add(final String userId,
                                         final com.runteam.core.api.Statistics userStatistics,
                                         final OffsetDateTime date) {
        final User user = this.userRepository.findById(new UserId(userId));
        final Statistics statistics = this.domainStatisticsAdapter.adaptFromApi(userStatistics);

        final User domainUser = this.addStatisticsToUser.add(user, statistics, date);

        this.userRepository.save(domainUser);

        return this.apiUserAdapter.adaptFromDomain(domainUser);
    }
}
