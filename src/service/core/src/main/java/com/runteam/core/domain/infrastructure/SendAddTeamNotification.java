package com.runteam.core.domain.infrastructure;

import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.User;

public interface SendAddTeamNotification {
	boolean send(final User user, final Team team, final User newUser);
}
