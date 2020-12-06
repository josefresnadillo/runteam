package com.runteam.backend.domain;

import java.time.OffsetDateTime;

// Aggregate

public class User {
    private final UserId id;
    private final UserCredentials credentials;
    private final UserDetails details;
    private final UserStatus status;
    private final UserPrivacy privacy;
    private final OffsetDateTime activationDate;
    private final UserStatistics statistics;



}
