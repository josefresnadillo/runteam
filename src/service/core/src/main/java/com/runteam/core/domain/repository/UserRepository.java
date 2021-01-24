package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;

public interface UserRepository {

	void save(final User user);

	void delete(final User user);

	User findById(final UserId userId);
}
