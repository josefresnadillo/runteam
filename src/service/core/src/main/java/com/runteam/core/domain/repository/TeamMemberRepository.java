package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.TeamMember;
import com.runteam.core.domain.model.TeamMemberId;
import com.runteam.core.domain.model.UserId;
import java.util.List;

public interface TeamMemberRepository {

	boolean save(final TeamMember teamMember);

	TeamMember findById(final TeamMemberId teamMemberId);

	List<TeamMember> findByUserId(final UserId userId);

	List<TeamMember> findByTeamId(final TeamId teamId);

	TeamMember findByTeamIdAndUserId(final TeamId teamId,
	                                 final UserId userId);
}
