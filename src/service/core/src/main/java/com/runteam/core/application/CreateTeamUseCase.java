package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiTeamAdapter;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamDetails;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.TeamRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.CreateTeam;

public class CreateTeamUseCase {

    private final CreateTeam createTeam;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ApiTeamAdapter apiTeamAdapter;

    public CreateTeamUseCase(final CreateTeam createTeam,
                             final UserRepository userRepository,
                             final TeamRepository teamRepository,
                             final ApiTeamAdapter apiTeamAdapter) {
        this.createTeam = createTeam;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.apiTeamAdapter = apiTeamAdapter;
    }

    public com.runteam.core.api.Team createActive(final String userId,
                                                  final String name,
                                                  final String displayName,
                                                  final com.runteam.core.api.TeamDetails apiDetails) {
        final User owner = this.userRepository.findById(new UserId(userId));
        final TeamDetails details = TeamDetails.builder()
                .name(name)
                .displayName(displayName)
                .imageUrl(apiDetails.getImageUrl())
                .city(apiDetails.getCity())
                .countryCode(apiDetails.getCountryCode())
                .build();

        final Team newTeam = this.createTeam.active(owner, details);

        this.teamRepository.save(newTeam);

        return this.apiTeamAdapter.adaptFromDomain(newTeam, owner);
    }
}
