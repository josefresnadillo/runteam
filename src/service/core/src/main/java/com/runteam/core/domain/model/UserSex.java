package com.runteam.core.domain.model;

// Value Object

import java.util.Arrays;

public enum UserSex {
	MALE,
	FEMALE,
	OTHER;

	public static UserSex adapt(final String sex){
		return Arrays.stream(UserSex.values())
				.filter(userSex -> userSex.name().equalsIgnoreCase(sex))
				.findFirst()
				.orElse(UserSex.OTHER);
	}
}
