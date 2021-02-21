package com.runteam.core.application;

import com.runteam.core.application.adapter.ApiChallengeTeamMemberAdapter;
import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.*;
import com.runteam.core.domain.service.AddTeamToChallenge;

public class AddTeamToChallengeUseCase {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeMemberRepository challengeMemberRepository;
    private final AddTeamToChallenge addTeamToChallenge;
    private final ApiChallengeTeamMemberAdapter apiChallengeTeamMemberAdapter;

    public AddTeamToChallengeUseCase(final UserRepository userRepository,
                                     final TeamRepository teamRepository,
                                     final ChallengeRepository challengeRepository,
                                     final ChallengeMemberRepository challengeMemberRepository,
                                     final AddTeamToChallenge addTeamToChallenge,
                                     final ApiChallengeTeamMemberAdapter apiChallengeTeamMemberAdapter) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.challengeRepository = challengeRepository;
        this.challengeMemberRepository = challengeMemberRepository;
        this.addTeamToChallenge = addTeamToChallenge;
        this.apiChallengeTeamMemberAdapter = apiChallengeTeamMemberAdapter;
    }

    public com.runteam.core.api.ChallengeTeamMember addActive(final String managerUserId,
                                                              final String teamId,
                                                              final String challengeId) {
        final User managerUser = this.userRepository.findById(new UserId(managerUserId));
        final Team team = this.teamRepository.findById(new TeamId(teamId));
        final User owner = this.userRepository.findById(team.getOwnerId());
        final Challenge challenge = this.challengeRepository.findById(new ChallengeId(challengeId));

        final ChallengeMember challengeMember = this.addTeamToChallenge.add(managerUser, challenge, team);
        this.challengeMemberRepository.save(challengeMember);

        return this.apiChallengeTeamMemberAdapter.adaptFromDomain(challengeMember, team, owner);
    }
}
