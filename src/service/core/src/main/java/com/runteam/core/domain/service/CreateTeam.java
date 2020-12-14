package com.runteam.core.domain.service;

import com.runteam.core.domain.model.*;
import com.runteam.core.domain.repository.TeamRepository;

import java.util.List;
import java.util.logging.Logger;

public class CreateTeam {

	private static final Logger LOGGER = Logger.getLogger(CreateTeam.class.toString());

	private final TeamRepository teamRepository;

	public CreateTeam(final TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public Team active(final User user,
	                   final TeamDetails details) {
		final List<Team> teams = teamRepository.findByOwnerId(user.getId());

		if (teams.size() >= user.getSubscriptionType().getMaxTeams()) {
			LOGGER.info(DomainExceptionCode.TOO_MANY_TEAMS + ": " + user);
			throw new DomainException(DomainExceptionCode.TOO_MANY_TEAMS);
		}

		final Team team = new Team(TeamId.randomTeamId(), user.getId());
		team.setDetails(details);
		team.setStatus(TeamStatus.ACTIVE);
		return team;
	}
}
