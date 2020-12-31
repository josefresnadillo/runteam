package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.ChallengeMember;
import com.runteam.core.domain.model.ChallengeMemberId;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import java.util.List;

public interface ChallengeMemberRepository {

	boolean save(final ChallengeMember challengeMember);

	Team findById(final ChallengeMemberId challengeMemberId);

	List<ChallengeMember> findByChallengeId(final ChallengeId challengeId);

	ChallengeMember findByChallengeIdAndTeamId(final ChallengeId challengeId,
	                                           final TeamId teamId);
}
