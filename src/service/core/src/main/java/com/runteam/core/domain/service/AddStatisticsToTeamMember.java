package com.runteam.core.domain.service;

import com.runteam.core.domain.event.StatisticsTeamMemberEvent;
import com.runteam.core.domain.model.*;

import java.time.OffsetDateTime;

public class AddStatisticsToTeamMember {

    private final DomainEvents domainEvents;

    public AddStatisticsToTeamMember(final DomainEvents domainEvents) {
        this.domainEvents = domainEvents;
    }

    public TeamMember add(final TeamMember teamMember,
                          final Statistics statistics,
                          final OffsetDateTime date) {

        // Check if it is active
        if (!teamMember.isActive()) {
            return teamMember;
        }

        // Check if it created before
        if (!teamMember.getCreationDate().isBefore(date)) {
            return teamMember;
        }

        // add statistics
        teamMember.addStatistics(statistics);

        // Send event
        this.domainEvents.sendEvent(new StatisticsTeamMemberEvent(teamMember.getId(), date, statistics));

        return teamMember;
    }
}
