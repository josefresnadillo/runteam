package com.runteam.backend.domain;

import java.util.List;

public class Team {
    private final TeamId id;
    private final String name;
    private final String displayName;
    private final TeamStatus status;
    private final String imageUrl;
    private final List<TeamMember> members;
    private final Statistics statistics;

    public Team(final TeamId id,
                final String name,
                final String displayName,
                final TeamStatus status,
                final String imageUrl,
                final List<TeamMember> members,
                final Statistics statistics){
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.status = status;
        this.imageUrl = imageUrl;
        this.members = members;
        this.statistics = statistics;
    }

    public TeamId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
