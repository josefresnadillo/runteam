package com.runteam.core.domain.service;

import com.runteam.core.domain.event.StatisticsUserEvent;
import com.runteam.core.domain.model.*;

import java.time.OffsetDateTime;

public class AddStatisticsToUser {

    private final DomainEvents domainEvents;

    public AddStatisticsToUser(final DomainEvents domainEvents) {
        this.domainEvents = domainEvents;
    }

    public User add(final User user,
                    final Statistics statistics,
                    final OffsetDateTime date) {

        // Check if manager user is active
        user.checkIsActiveOrThrow();

        // Add statistics to user
        user.addStatistics(statistics);

        // Send events
        this.domainEvents.sendEvent(new StatisticsUserEvent(user.getId(), date, statistics));

        return user;
    }
}
