package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiUserTeamMemberAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.TeamMemberRepository;
import com.runteam.core.domain.repository.TeamRepository;
import com.runteam.core.domain.repository.UserRepository;
import com.runteam.core.domain.service.AddUserToTeam;

public class AddUserToTeamUseCase {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final AddUserToTeam addUserToTeam;
    private final ApiUserTeamMemberAdapter apiUserTeamMemberAdapter;

    public AddUserToTeamUseCase(final UserRepository userRepository,
                                final TeamRepository teamRepository,
                                final TeamMemberRepository teamMemberRepository,
                                final AddUserToTeam addUserToTeam,
                                final ApiUserTeamMemberAdapter apiUserTeamMemberAdapter) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.addUserToTeam = addUserToTeam;
        this.apiUserTeamMemberAdapter = apiUserTeamMemberAdapter;
    }

    public com.runteam.core.api.UserTeamMember addActive(final String managerUserId,
                                                         final String userId,
                                                         final String teamId) {
        final User managerUser = this.userRepository.findById(new UserId(managerUserId));
        final User user = this.userRepository.findById(new UserId(userId));
        final Team team = this.teamRepository.findById(new TeamId(teamId));

        final TeamMember teamMember = this.addUserToTeam.add(managerUser, team, user);
        this.teamMemberRepository.save(teamMember);

        return this.apiUserTeamMemberAdapter.adaptFromDomain(teamMember, user);
    }
}
