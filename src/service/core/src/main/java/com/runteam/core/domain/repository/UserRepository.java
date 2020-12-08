package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.User;
import com.runteam.core.domain.model.UserId;

public interface UserRepository {
    boolean save(final User user);
    boolean delete(final User user);
    User findById(final UserId userId);
}
