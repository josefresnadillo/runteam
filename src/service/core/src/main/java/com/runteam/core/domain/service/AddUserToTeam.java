package com.runteam.core.domain.service;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.logging.Logger;

public class AddUserToTeam {

	private static final Logger LOGGER = Logger.getLogger(AddUserToTeam.class.toString());

	private final UserRepository userRepository;

	public AddUserToTeam(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Team add(final UserId userId,
	                final Team team,
	                final UserId newUserToTeam) {

		// If team is private, only the owner can add a new user
		final boolean isTeamOwner = team.getOwnerId().equals(userId);
		if ((team.getPrivacy() == Privacy.PRIVATE) && (!isTeamOwner)){
			LOGGER.info(DomainExceptionCode.TEAM_IS_PRIVATE + ": " + team);
			throw new DomainException(DomainExceptionCode.TEAM_IS_PRIVATE);
		}

		// Check if team has too many users
		// Depends on the team owner subscription
		final User owner = userRepository.findById(team.getOwnerId());
		if (team.getActiveMembers().size() >= owner.getSubscriptionType().getMaxTeamsUserBelongs()){
			LOGGER.info(DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS + ": " + team);
			throw new DomainException(DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS);
		}

		team.addMember(newUserToTeam, OffsetDateTime.now());
		return team;
	}
}
