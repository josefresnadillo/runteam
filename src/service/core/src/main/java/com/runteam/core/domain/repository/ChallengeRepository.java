package com.runteam.core.domain.repository;

import com.runteam.core.domain.model.Challenge;
import com.runteam.core.domain.model.ChallengeId;
import com.runteam.core.domain.model.UserId;
import java.util.List;

public interface ChallengeRepository {
    boolean save(final Challenge challenge);
    boolean delete(final Challenge challenge);
    Challenge findById(final ChallengeId challengeId);
    List<Challenge> findByOwnerId(final UserId userId);
}
