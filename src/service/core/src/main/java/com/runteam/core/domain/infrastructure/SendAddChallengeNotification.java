package com.runteam.core.domain.infrastructure;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.Team;
import com.runteam.core.domain.model.User;

public interface SendAddChallengeNotification {
	boolean send(final User owner, final Challenge challenge, final Team team);
}
