package com.runteam.core.domain;

// Value Object

import java.util.Objects;

public class TeamDetails {

    public static final TeamDetails EMPTY = TeamDetails.builder().build();

    private final String name; // example "@team1"
    private final String displayName; // example "The Best Team"
    private final String imageUrl;
    private final String city;
    private final String countryCode; // ISO 3166-1-alpha-2 2 letters

    private TeamDetails(final String name,
                        final String displayName,
                        final String imageUrl,
                        final String city,
                        final String countryCode) {
        this.name = name;
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDetails that = (TeamDetails) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(city, that.city) &&
                Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, imageUrl, city, countryCode);
    }

    @Override
    public String toString() {
        return "TeamDetails{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name; // example "@team1"
        private String displayName; // example "The Best Team"
        private String imageUrl;
        private String city;
        private String countryCode; // ISO 3166-1-alpha-2 2 letters

        public Builder() {
        }

        public Builder name(final String value) {
            this.name = value;
            return this;
        }

        public Builder displayName(final String value) {
            this.displayName = value;
            return this;
        }

        public Builder imageUrl(final String value) {
            this.imageUrl = value;
            return this;
        }

        public Builder city(final String value) {
            this.city = value;
            return this;
        }

        public Builder countryCode(final String value) {
            this.countryCode = value;
            return this;
        }

        public TeamDetails build() {
            return new TeamDetails(this.name,
                    this.displayName,
                    this.imageUrl,
                    this.city,
                    this.countryCode);
        }
    }
}