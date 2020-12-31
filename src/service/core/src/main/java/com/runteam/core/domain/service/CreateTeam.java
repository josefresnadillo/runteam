package com.runteam.core.domain.service;

import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamDetails;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;

public class CreateTeam {

	public CreateTeam() {
	}

	public Team active(final User managerUser,
	                   final TeamDetails details) {

		// Check manager user is active
		managerUser.checkIsActiveOrThrow();

		// Check if the user has created too many teams
		managerUser.checkNumberOfTeamsCreatedOrThrow();

		// Create team
		return createTeam(managerUser.getId(),
		                  details);
	}

	private Team createTeam(final UserId userId,
	                        final TeamDetails details) {
		final Team team = new Team(TeamId.randomId(),
		                           userId,
		                           0,
		                           0);
		team.setDetails(details);
		team.setStatus(Status.ACTIVE);
		return team;
	}
}
