package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import java.time.OffsetDateTime;

public class AddStatisticsToChallengeMember {

    public AddStatisticsToChallengeMember(){
    }

    public ChallengeMember add(final ChallengeMember challengeMember,
                               final Statistics statistics,
                               final OffsetDateTime date) {

        if (!challengeMember.isActive()) {
            return challengeMember;
        }

        if (!challengeMember.getCreationDate().isBefore(date)) {
            return challengeMember;
        }

        challengeMember.addStatistics(statistics);
        return challengeMember;
    }
}
