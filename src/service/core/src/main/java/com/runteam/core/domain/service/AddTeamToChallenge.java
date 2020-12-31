package com.runteam.core.domain.service;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeMember;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.repository.ChallengeMemberRepository;
import com.runteam.core.domain.repository.UserRepository;
import java.util.logging.Logger;

public class AddTeamToChallenge {

	private static final Logger LOGGER = Logger.getLogger(AddTeamToChallenge.class.toString());

	private final UserRepository userRepository;
	private final ChallengeMemberRepository challengeMemberRepository;

	public AddTeamToChallenge(final UserRepository userRepository,
	                          final ChallengeMemberRepository challengeMemberRepository) {
		this.userRepository = userRepository;
		this.challengeMemberRepository = challengeMemberRepository;
	}

	public ChallengeMember add(final User managerUser,
	                           final Challenge challenge,
	                           final Team team) {

		// Team owner is the only who can add a team to a challenge
		team.checkOwnerOrThrow(managerUser.getId());

		// Check if manager user is active
		managerUser.checkIsActiveOrThrow();

		// Check if team is active
		team.checkIsActiveOrThrow();

		// Check if challenge is active
		challenge.checkIsActiveOrThrow();

		// Check if team belong to too many challenges
		checkTeamInTooManyChallenges(team);

		// Retrieve challenge member or create
		final ChallengeMember member = retrieveMemberOrCreate(challenge, team.getId());

		// Check if the team is already active in the challenge
		checkMemberAlreadyActiveInChallenge(member);

		// Add the team as PENDING or ACTIVE
		// Depending on if the challenge is private to the manager user
		member.setStatus(Status.PENDING);
		if (!challenge.isPrivate(managerUser.getId())){
			// Check the number of ACTIVE teams in the challenge
			checkChallengeHasTooManyTeams(challenge);
			// Add as ACTIVE
			member.setStatus(Status.ACTIVE);
		}

		return member;
	}

	// Retrieve the challenge member
	// If empty, then create one
	private ChallengeMember retrieveMemberOrCreate(final Challenge challenge,
	                                               final TeamId newTeamId) {
		final ChallengeMember challengeMember = challengeMemberRepository.findByChallengeIdAndTeamId(challenge.getId(), newTeamId);
		return challengeMember.isEmpty() ?
		       challenge.addMember(newTeamId, Status.INACTIVE) :
		       challengeMember;
	}

	// Check if team is in too many challenges
	// Depends on the team owner subscription type
	private void checkTeamInTooManyChallenges(final Team team) {
		final User teamOwner = userRepository.findById(team.getOwnerId());
		if (team.getNumberOfMemberships() >= teamOwner.getSubscriptionType().getMaxChallengesTeamCanBelong()) {
			LOGGER.info(DomainExceptionCode.TEAM_IN_TOO_MANY_CHALLENGES + ": " + team);
			throw new DomainException(DomainExceptionCode.TEAM_IN_TOO_MANY_CHALLENGES);
		}
	}

	// Check if the challenge has too many teams
	// Depends on the challenge owner subscription type
	private void checkChallengeHasTooManyTeams(final Challenge challenge) {
		final User challengeOwner = userRepository.findById(challenge.getOwnerId());
		if (challenge.getNumberOfMembers() >= challengeOwner.getSubscriptionType().getMaxChallengeTeams()) {
			LOGGER.info(DomainExceptionCode.CHALLENGE_HAS_TOO_MANY_TEAMS + ": " + challenge);
			throw new DomainException(DomainExceptionCode.CHALLENGE_HAS_TOO_MANY_TEAMS);
		}
	}

	// Check if team is already active in challenge
	private void checkMemberAlreadyActiveInChallenge(final ChallengeMember member) {
		if (member.getStatus() == Status.ACTIVE) {
			LOGGER.info(DomainExceptionCode.TEAM_ALREADY_IN_CHALLENGE + ": " + member);
			throw new DomainException(DomainExceptionCode.TEAM_ALREADY_IN_CHALLENGE);
		}
	}
}
