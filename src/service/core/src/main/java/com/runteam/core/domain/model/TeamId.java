package com.runteam.core.domain.model;

// Value Object

public class TeamId extends GenericId{

    public TeamId(final String id){
        super(id);
    }

    public static TeamId randomTeamId(){
        return new TeamId(generate());
    }
}
