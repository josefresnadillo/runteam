package com.runteam.backend.domain;

import java.time.LocalDate;

public class UserDetails {
    private final String name;
    private final String surname;
    private final String email;
    private final String imageUrl;
    private final LocalDate birthday;
    private final Sex sex;
    private final String countryCode; // iso 3166

    public UserDetails(final String name,
                       final String surname,
                       final String email,
                       final String imageUrl,
                       final LocalDate birthday,
                       final Sex sex,
                       final String countryCode){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.birthday = birthday;
        this.sex = sex;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
