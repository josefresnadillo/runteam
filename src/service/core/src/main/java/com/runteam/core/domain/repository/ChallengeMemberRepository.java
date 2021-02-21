package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.ChallengeMember;
import com.runteam.core.domain.model.ChallengeMemberId;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import java.util.List;

public interface ChallengeMemberRepository {

	boolean save(final ChallengeMember challengeMember);

	ChallengeMember findById(final ChallengeMemberId challengeMemberId);

	List<ChallengeMember> findByChallengeId(final ChallengeId challengeId);

	List<ChallengeMember> findByTeamId(final TeamId teamId);

	List<ChallengeMember> findByTeamIds(final List<TeamId> teamIds);

	ChallengeMember findByChallengeIdAndTeamId(final ChallengeId challengeId,
	                                           final TeamId teamId);
}
