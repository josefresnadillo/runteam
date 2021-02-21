package com.runteam.core.application.adapter;

import com.runteam.core.domain.model.Statistics;

public class DomainStatisticsAdapter {

    public DomainStatisticsAdapter() {}

    public Statistics adaptFromApi(final com.runteam.core.api.Statistics apiStatistics) {
        return new Statistics(
                apiStatistics.getMeters(),
                apiStatistics.getElevation(),
                apiStatistics.getSeconds());
    }
}
