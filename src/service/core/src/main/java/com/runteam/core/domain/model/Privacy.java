package com.runteam.core.domain.model;

// Value Object

import java.util.Arrays;

public enum Privacy {
	PUBLIC,
	PRIVATE;

	public static Privacy adapt(final String apiPrivacy){
		return Arrays.stream(Privacy.values())
				.filter(privacy -> privacy.name().equalsIgnoreCase(apiPrivacy))
				.findFirst()
				.orElse(Privacy.PUBLIC);
	}
}
