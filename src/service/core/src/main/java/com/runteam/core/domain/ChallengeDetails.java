package com.runteam.core.domain;

import java.util.List;
import java.util.Objects;

// Value Object

public class ChallengeDetails {

    public static final ChallengeDetails EMPTY = new ChallengeDetails("", "", "", List.of());

    private final String name; // Example @challenge1
    private final String displayName; // Example The best challenge
    private final String imageUrl;
    private final List<String> tags; // Example #runbest #100km

    public ChallengeDetails(final String name,
                            final String displayName,
                            final String imageUrl,
                            final List<String> tags) {
        this.name = name;
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.tags = tags;
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

    public List<String> getTags() {
        return tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeDetails that = (ChallengeDetails) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, imageUrl, tags);
    }

    @Override
    public String toString() {
        return "ChallengeDetails{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", tags=" + tags +
                '}';
    }
}
