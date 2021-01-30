package com.runteam.core.application.adapter;

import com.runteam.core.api.*;

public class ApiStatisticsAdapter {

    public ApiStatisticsAdapter() {}

    public Statistics adaptFromDomain(final com.runteam.core.domain.model.Statistics domainStatistics){
        return new Statistics()
                .elevation(domainStatistics.getElevationInMeters())
                .meters(domainStatistics.getTotalMeters())
                .seconds(domainStatistics.getTotalSeconds());
    }
}
