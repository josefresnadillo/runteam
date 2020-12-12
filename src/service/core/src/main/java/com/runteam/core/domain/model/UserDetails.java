package com.runteam.core.domain.model;

import java.time.LocalDate;
import java.util.Objects;

// Value Object

public class UserDetails {

	private final String displayName; // Example Jose Fresnadillo
	private final String email;
	private final String imageUrl;
	private final LocalDate birthday;
	private final UserSex sex;
	private final String city;
	private final String countryCode; // ISO 3166-1-alpha-2 2 letters
	private final String language; // ISO 639 2 letters

	private UserDetails(final String displayName,
	                    final String email,
	                    final String imageUrl,
	                    final LocalDate birthday,
	                    final UserSex sex,
	                    final String city,
	                    final String countryCode,
	                    final String language) {
		this.displayName = displayName;
		this.email = email;
		this.imageUrl = imageUrl;
		this.birthday = birthday;
		this.sex = sex;
		this.city = city;
		this.countryCode = countryCode;
		this.language = language;
	}

	public String getDisplayName() {
		return displayName;
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

	public UserSex getSex() {
		return sex;
	}

	public String getCity() {
		return city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
		UserDetails that = (UserDetails) o;
		return Objects.equals(displayName, that.displayName) &&
			Objects.equals(email, that.email) &&
			Objects.equals(imageUrl, that.imageUrl) &&
			Objects.equals(birthday, that.birthday) &&
			sex == that.sex &&
			Objects.equals(city, that.city) &&
			Objects.equals(countryCode, that.countryCode) &&
			Objects.equals(language, that.language);
	}

	@Override
	public int hashCode() {
		return Objects.hash(displayName, email, imageUrl, birthday, sex, city, countryCode, language);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String displayName = "";
		private String email = "";
		private String imageUrl = "";
		private LocalDate birthday = LocalDate.of(1970, 1, 1);
		private UserSex sex = UserSex.FEMALE;
		private String city = "";
		private String countryCode = "es"; // ISO 3166-1-alpha-2
		private String language = "es";

		public Builder() {
		}

		public Builder displayName(final String value) {
			this.displayName = value;
			return this;
		}

		public Builder email(final String value) {
			this.email = email;
			return this;
		}

		public Builder imageUrl(final String value) {
			this.imageUrl = imageUrl;
			return this;
		}

		public Builder birthday(final LocalDate value) {
			this.birthday = value;
			return this;
		}

		public Builder sex(final UserSex value) {
			this.sex = value;
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

		public Builder language(final String value) {
			this.language = value;
			return this;
		}

		public UserDetails build() {
			return new UserDetails(this.displayName,
			                       this.email,
			                       this.imageUrl,
			                       this.birthday,
			                       this.sex,
			                       this.city,
			                       this.countryCode,
			                       this.language);
		}
	}
}
