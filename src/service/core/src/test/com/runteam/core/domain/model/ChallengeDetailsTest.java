package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChallengeDetailsTest {

	@Test
	@DisplayName("Test name, display name, tags ok")
	public void setNameDisplayNameCityTest() {
		final String name = "team1";
		final String displayName = "Best Team";
		final List<String> tags = List.of("tag1", "tag2");
		final ChallengeDetails challengeDetails = ChallengeDetails.builder()
		                                                          .name(name)
		                                                          .displayName(displayName)
		                                                          .tags(tags)
		                                                          .build();
		assertEquals(challengeDetails.getName(), name);
		assertEquals(challengeDetails.getDisplayName(), displayName);
		assertEquals(challengeDetails.getTags(), tags);
	}

	@Test
	@DisplayName("Test set url ok")
	public void setImageUrlOkTest() {
		final ChallengeDetails challengeDetails = ChallengeDetails.builder()
		                                                          .imageUrl("http://google.es")
		                                                          .build();
		assertEquals(challengeDetails.getImageUrl(), "http://google.es");
	}

	@Test
	@DisplayName("Test set url wrong")
	public void setImageUrlWroingTest() {
		assertThrows(DomainException.class, () -> ChallengeDetails.builder()
		                                                          .imageUrl("wrong")
		                                                          .build());
	}
}
