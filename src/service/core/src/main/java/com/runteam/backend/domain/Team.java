package com.runteam.backend.domain;

import java.time.OffsetDateTime;
import java.util.List;

// Aggregate

public class Team {
    private final TeamId id;
    private final UserId owner;
    private final TeamDetails details;
    private final TeamStatus status;
    private final OffsetDateTime activationDate;
    private final List<TeamMember> activeMembers;
    private final List<TeamMember> inactiveMembers;
    private final List<TeamMember> applicantMembers;

}
