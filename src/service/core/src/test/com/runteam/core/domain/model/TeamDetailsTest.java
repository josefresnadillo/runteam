package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamDetailsTest {

	@Test
	@DisplayName("Test name, display name, city ok")
	public void setNameDisplayNameCityTest() {
		final String name = "team1";
		final String displayName = "Best Team";
		final String city = "Madrid";
		final TeamDetails teamDetails = TeamDetails.builder()
		                                           .name(name)
		                                           .displayName(displayName)
		                                           .city(city)
		                                           .build();
		assertEquals(teamDetails.getName(), name);
		assertEquals(teamDetails.getDisplayName(), displayName);
		assertEquals(teamDetails.getCity(), city);
	}

	@Test
	@DisplayName("Test set country ok")
	public void setCountryCodeOkTest() {
		final TeamDetails teamDetails = TeamDetails.builder()
		                                           .countryCode("es")
		                                           .build();
		assertEquals(teamDetails.getCountryCode(), "es");
	}

	@Test
	@DisplayName("Test set country wrong")
	public void setCountryCodeWrongTest() {
		DomainException exception = assertThrows(DomainException.class, () -> TeamDetails.builder()
		                                                     .countryCode("wrong")
		                                                     .build());
		assertEquals(exception.getCode(), DomainExceptionCode.COUNTRY_NOT_VALID);
	}

	@Test
	@DisplayName("Test set url ok")
	public void setImageUrlOkTest() {
		final TeamDetails teamDetails = TeamDetails.builder()
		                                           .imageUrl("http://google.es")
		                                           .build();
		assertEquals(teamDetails.getImageUrl(), "http://google.es");
	}

	@Test
	@DisplayName("Test set url wrong")
	public void setImageUrlWroingTest() {
		DomainException exception = assertThrows(DomainException.class, () -> TeamDetails.builder()
		                                                     .imageUrl("wrong")
		                                                     .build());
		assertEquals(exception.getCode(), DomainExceptionCode.URL_NOT_VALID);
	}
}
