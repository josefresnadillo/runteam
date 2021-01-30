package com.runteam.core.application;

import com.runteam.core.api.Team;
import com.runteam.core.api.TeamDetails;
import com.runteam.core.application.adapter.ApiTeamAdapter;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.TeamRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.CreateTeam;

public class TeamService {

    private final CreateTeam createTeam;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ApiTeamAdapter apiTeamAdapter;

    public TeamService(final CreateTeam createTeam,
                       final UserRepository userRepository,
                       final TeamRepository teamRepository,
                       final ApiTeamAdapter apiTeamAdapter) {
        this.createTeam = createTeam;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.apiTeamAdapter = apiTeamAdapter;
    }

    public Team createTeam(final String userId,
                           final String name,
                           final String displayName,
                           final TeamDetails apiDetails) {

        final com.runteam.core.domain.model.User owner = userRepository.findById(new UserId(userId));

        final com.runteam.core.domain.model.TeamDetails details = com.runteam.core.domain.model.TeamDetails.builder()
                .name(name)
                .displayName(displayName)
                .imageUrl(apiDetails.getImageUrl())
                .city(apiDetails.getCity())
                .countryCode(apiDetails.getCountryCode())
                .build();

        final com.runteam.core.domain.model.Team newTeam = createTeam.active(owner, details);

        this.teamRepository.save(newTeam);

        return this.apiTeamAdapter.adaptFromDomain(newTeam, owner);
    }
}
