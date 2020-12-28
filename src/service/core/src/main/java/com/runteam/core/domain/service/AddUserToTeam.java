package com.runteam.core.domain.service;

import com.runteam.core.domain.infrastructure.SendAddTeamNotification;
import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamMember;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AddUserToTeam {

	private static final Logger LOGGER = Logger.getLogger(AddUserToTeam.class.toString());

	private final UserRepository userRepository;
	private final SendAddTeamNotification sendAddTeamNotification;

	public AddUserToTeam(final UserRepository userRepository,
	                     final SendAddTeamNotification sendAddTeamNotification) {
		this.userRepository = userRepository;
		this.sendAddTeamNotification = sendAddTeamNotification;
	}

	public Team add(final UserId userId,
	                final Team team,
	                final UserId newUserId) {

		// Check if team has too many users
		// Depends on the team owner subscription
		final User owner = userRepository.findById(team.getOwnerId());
		if (team.getActiveMembers().size() >= owner.getSubscriptionType().getMaxTeamsUserBelongs()) {
			LOGGER.info(DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS + ": " + team);
			throw new DomainException(DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS);
		}

		// Check if user is already active in team
		final List<UserId> activeUsers = team.getActiveMembers().stream()
		                                     .map(TeamMember::getId)
		                                     .collect(Collectors.toList());
		if (activeUsers.contains(newUserId)){
			LOGGER.info(DomainExceptionCode.USER_ALREADY_IN_TEAM + ": " + team);
			throw new DomainException(DomainExceptionCode.USER_ALREADY_IN_TEAM);
		}

		// If team is private, only the owner can add a new user
		final boolean isTeamOwner = team.getOwnerId().equals(userId);
		if ((team.getPrivacy() == Privacy.PRIVATE) && (!isTeamOwner)) {
			final User newUser = userRepository.findById(newUserId);
			sendAddTeamNotification.send(owner, team, newUser);
			team.addPendingMember(newUserId, OffsetDateTime.now());
		} else {
			team.addActiveMember(newUserId, OffsetDateTime.now());
		}

		return team;
	}
}
