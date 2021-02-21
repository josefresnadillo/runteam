package com.runteam.core.application.adapter;

import com.runteam.core.api.*;

public class ApiPersonalBestAdapter {

    public ApiPersonalBestAdapter() {}

    public PersonalBest adaptFromDomain(final com.runteam.core.domain.model.PersonalBest domainPersonalBest) {
        return new PersonalBest()
                .best1kInSeconds(domainPersonalBest.getBest1kInSeconds())
                .best5kInSeconds(domainPersonalBest.getBest5kInSeconds())
                .best10kInSeconds(domainPersonalBest.getBest10kInSeconds())
                .bestMMInSeconds(domainPersonalBest.getBestHalfMarathonInSeconds())
                .bestMMInSeconds(domainPersonalBest.getBestMarathonInSeconds());
    }
}
