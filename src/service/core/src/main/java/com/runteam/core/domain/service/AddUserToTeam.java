package com.runteam.core.domain.service;

import com.runteam.core.domain.model.DomainException;
import com.runteam.core.domain.model.DomainExceptionCode;
import com.runteam.core.domain.model.Status;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamMember;
import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;
import com.runteam.core.domain.repository.TeamMemberRepository;
import com.runteam.core.domain.repository.UserRepository;
import java.util.logging.Logger;

public class AddUserToTeam {

	private static final Logger LOGGER = Logger.getLogger(AddUserToTeam.class.toString());

	private final UserRepository userRepository;
	private final TeamMemberRepository teamMemberRepository;

	public AddUserToTeam(final UserRepository userRepository,
	                     final TeamMemberRepository teamMemberRepository) {
		this.userRepository = userRepository;
		this.teamMemberRepository = teamMemberRepository;
	}

	public TeamMember add(final User managerUser,
	                      final Team team,
	                      final User newUser) {

		// Check if manager user is active
		managerUser.checkIsActiveOrThrow();

		// Check if new user is active
		newUser.checkIsActiveOrThrow();

		// Check if team is active
		team.checkIsActiveOrThrow();

		// Check if the user belongs to too many teams
		newUser.checkNumberOfMembershipsOrThrow();

		// Retrieve member
		final TeamMember member = retrieveMemberOrCreate(team, newUser.getId());

		// Check if the user is already active in the team
		checkMemberAlreadyActiveInTeam(member);

		// Add the user as PENDING or ACTIVE
		// Depending on if the team is private to the manager user
		member.setStatus(Status.PENDING);
		if (!team.isPrivate(managerUser.getId())) {
			// Check the number of ACTIVE users in the team
			checkUsersInTeam(team);
			// Add as ACTIVE
			member.setStatus(Status.ACTIVE);
		}

		return member;
	}

	// Retrieve team member
	// If empty, then create one
	private TeamMember retrieveMemberOrCreate(final Team team,
	                                          final UserId newUserId) {
		final TeamMember teamMember = teamMemberRepository.findByTeamIdAndUserId(team.getId(), newUserId);
		return teamMember.isEmpty() ?
		       team.addMember(newUserId, Status.INACTIVE) :
		       teamMember;
	}

	// Check if user is already active in team
	private void checkMemberAlreadyActiveInTeam(final TeamMember member) {
		if (member.getStatus() == Status.ACTIVE) {
			LOGGER.info(DomainExceptionCode.USER_ALREADY_IN_TEAM + ": " + member);
			throw new DomainException(DomainExceptionCode.USER_ALREADY_IN_TEAM);
		}
	}

	// Check if team has too many users
	// Depends on the team owner subscription
	private void checkUsersInTeam(final Team team) {
		final User owner = userRepository.findById(team.getOwnerId());
		if (team.getNumberOfMembers() >= owner.getSubscriptionType().getMaxTeamMembers()) {
			LOGGER.info(DomainExceptionCode.TEAM_HAS_TOO_MANY_USERS + ": " + team);
			throw new DomainException(DomainExceptionCode.TEAM_HAS_TOO_MANY_USERS);
		}
	}
}
