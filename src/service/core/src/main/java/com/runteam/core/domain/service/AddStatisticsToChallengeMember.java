package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import java.time.OffsetDateTime;

public class AddStatisticsToChallengeMember {

    public AddStatisticsToChallengeMember(){
    }

    public ChallengeMember add(final ChallengeMember challengeMember,
                               final Statistics statistics,
                               final OffsetDateTime date) {
        if (checkTeamMember(challengeMember, date)) {
            return challengeMember;
        }
        challengeMember.addStatistics(statistics);
        return challengeMember;
    }

    private boolean checkTeamMember(final ChallengeMember challengeMember,
                                    final OffsetDateTime date) {
        return ((challengeMember.isActive()) || challengeMember.getCreationDate().isBefore(date));
    }
}
