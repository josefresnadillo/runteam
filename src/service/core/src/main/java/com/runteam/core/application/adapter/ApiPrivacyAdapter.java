package com.runteam.core.application.adapter;

import com.runteam.core.api.Privacy;
import java.util.Arrays;

public class ApiPrivacyAdapter {

    public ApiPrivacyAdapter() {}

    public Privacy adaptFromDomain(final com.runteam.core.domain.model.Privacy domainPrivacy){
        return Arrays.stream(Privacy.values())
                .filter(privacy -> privacy.name().equalsIgnoreCase(domainPrivacy.name()))
                .findFirst()
                .orElse(Privacy.PUBLIC);
    }
}
