package com.runteam.core.domain.service;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Privacy;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.TeamRepository;
import com.runteam.core.domain.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.logging.Logger;

public class AddUserToTeam {

	private static final Logger LOGGER = Logger.getLogger(AddUserToTeam.class.toString());

	private final UserRepository userRepository;
	private final TeamRepository teamRepository;

	public AddUserToTeam(final UserRepository userRepository,
	                     final TeamRepository teamRepository) {
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
	}

	public Team add(final User user,
	                final Team team,
	                final UserId newUserToTeam) {

		// Team is public or private
		boolean isAllowed = true;
		if (team.getPrivacy() == Privacy.PRIVATE) {
			final List<Team> myTeams = teamRepository.findByOwnerId(user.getId());
			isAllowed = myTeams.stream().anyMatch(myTeam -> myTeam.getId().equals(team.getId()));
		}

		if (!isAllowed) {
			LOGGER.info(DomainExceptionCode.TEAM_IS_PRIVATE + ": " + team);
			throw new DomainException(DomainExceptionCode.TEAM_IS_PRIVATE);
		}

		// Team has too many users
		// Depends on the team owner subscription
		final User owner = userRepository.findById(team.getOwner());
		if (team.getActiveMembers().size() >= owner.getSubscriptionType().getMaxUserTeams()){
			LOGGER.info(DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS + ": " + team);
			throw new DomainException(DomainExceptionCode.TOO_MANY_USERS_IN_TEAMS);
		}

		team.addMember(newUserToTeam, OffsetDateTime.now());
		return team;
	}
}
