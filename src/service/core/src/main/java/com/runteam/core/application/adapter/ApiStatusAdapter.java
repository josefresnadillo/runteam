package com.runteam.core.application.adapter;

import com.runteam.core.api.*;
import java.util.Arrays;

public class ApiStatusAdapter {

    public ApiStatusAdapter() {}

    public Status adaptFromDomain(final com.runteam.core.domain.model.Status domainStatus){
        return Arrays.stream(Status.values())
                .filter(status -> status.name().equalsIgnoreCase(domainStatus.name()))
                .findFirst()
                .orElse(Status.INACTIVE);
    }
}
