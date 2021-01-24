package com.runteam.core.application.adapter;

import com.runteam.core.domain.model.UserDetails;
import com.runteam.core.domain.model.UserSex;

import java.time.LocalDate;

public class DomainUserDetailsAdapter {

    public DomainUserDetailsAdapter() {}

    public UserDetails adaptFromApi(final String displayName,
                                    final com.runteam.core.api.UserDetails apiDetails) {
        return UserDetails.builder()
                .displayName(displayName)
                .email(apiDetails.getEmail())
                .imageUrl(apiDetails.getImageUrl())
                .birthday(LocalDate.parse(apiDetails.getBirthday()))
                .sex(UserSex.adapt(apiDetails.getSex()))
                .language(apiDetails.getLanguage())
                .city(apiDetails.getCity())
                .countryCode(apiDetails.getCountryCode())
                .language(apiDetails.getLanguage())
                .build();
    }
}
