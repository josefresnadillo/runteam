package com.runteam.core.domain.service;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.ChallengeRepository;
import com.runteam.core.domain.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.logging.Logger;

public class AddTeamToChallenge {

	private static final Logger LOGGER = Logger.getLogger(AddTeamToChallenge.class.toString());

	private final UserRepository userRepository;
	private final ChallengeRepository challengeRepository;

	public AddTeamToChallenge(final UserRepository userRepository,
	                          final ChallengeRepository challengeRepository) {
		this.userRepository = userRepository;
		this.challengeRepository = challengeRepository;
	}

	public Challenge add(final UserId userId,
	                     final Challenge challenge,
	                     final Team team) {

		// If challenge is private, only the owner can add a new team
		final boolean isChallengeOwner = challenge.getOwner().equals(userId);
		if ((challenge.getPrivacy() == Privacy.PRIVATE) && (!isChallengeOwner)) {
			LOGGER.info(DomainExceptionCode.CHALLENGE_IS_PRIVATE + ": " + challenge);
			throw new DomainException(DomainExceptionCode.CHALLENGE_IS_PRIVATE);
		}

		// Check if the challenge has too many teams
		// Depends on the challenge owner subscription
		final User challengeOwner = userRepository.findById(challenge.getOwner());
		if (challenge.getActiveMembers().size() >= challengeOwner.getSubscriptionType().getMaxChallengeTeams()){
			LOGGER.info(DomainExceptionCode.CHALLENGE_HAS_TOO_MANY_TEAMS + ": " + challenge);
			throw new DomainException(DomainExceptionCode.CHALLENGE_HAS_TOO_MANY_TEAMS);
		}

		// Check if team is in too many challenges
		// Depends on the team owner subscription
		final User owner = userRepository.findById(team.getOwner());
		final List<Challenge> teamChallenges = challengeRepository.findByTeamId(team.getId());
		if (teamChallenges.size() >= owner.getSubscriptionType().getMaxTeamChallenges()){
			LOGGER.info(DomainExceptionCode.TEAM_IN_TOO_MANY_CHALLENGES + ": " + team);
			throw new DomainException(DomainExceptionCode.TEAM_IN_TOO_MANY_CHALLENGES);
		}

		challenge.addMember(team.getId(), OffsetDateTime.now());
		return challenge;
	}
}
