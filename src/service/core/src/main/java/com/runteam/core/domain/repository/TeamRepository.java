package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.TeamId;
import com.runteam.core.domain.model.UserId;

import java.util.List;

public interface TeamRepository {

	boolean save(final Team team);

	boolean delete(final Team team);

	Team findById(final TeamId teamId);

	List<Team> findByOwnerId(final UserId userId);
}
