package com.runteam.core.domain.model;

import static com.runteam.core.domain.model.DomainExceptionCode.URL_NOT_VALID;

import java.util.List;
import java.util.Objects;
import org.apache.commons.validator.routines.UrlValidator;

// Value Object

public class ChallengeDetails {

	private final String name; // Example @challenge1
	private final String displayName; // Example The best challenge
	private final String imageUrl;
	private final List<String> tags; // Example #runbest #100km

	private ChallengeDetails(final String name,
	                        final String displayName,
	                        final String imageUrl,
	                        final List<String> tags) {
		checkImageUrl(imageUrl);
		this.name = name;
		this.displayName = displayName;
		this.imageUrl = imageUrl;
		this.tags = tags;
	}

	private void checkImageUrl(final String url){
		if (!UrlValidator.getInstance().isValid(url)) {
			throw new DomainException(URL_NOT_VALID);
		}
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
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String name = ""; // Example @challenge1
		private String displayName = ""; // Example The best challenge
		private String imageUrl = "";
		private List<String> tags = List.of(); // Example #runbest #100km

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

		public Builder tags(final List<String> values) {
			this.tags = values;
			return this;
		}

		public ChallengeDetails build() {
			return new ChallengeDetails(this.name,
			                            this.displayName,
			                            this.imageUrl,
			                            this.tags);
		}
	}
}
