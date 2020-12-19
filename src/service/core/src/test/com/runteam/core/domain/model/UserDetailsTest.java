package com.runteam.core.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDetailsTest {

	@Test
	@DisplayName("Test name, display name, city ok")
	public void setNameDisplayNameCityBirthdaySexTest() {
		final String displayName = "Best Team";
		final String city = "Madrid";
		final UserDetails userDetails = UserDetails.builder()
		                                           .displayName(displayName)
		                                           .city(city)
		                                           .birthday(LocalDate.MAX)
		                                           .sex(UserSex.FEMALE)
		                                           .build();
		assertEquals(userDetails.getDisplayName(), displayName);
		assertEquals(userDetails.getCity(), city);
		assertEquals(userDetails.getBirthday(), LocalDate.MAX);
		assertEquals(userDetails.getSex(), UserSex.FEMALE);
	}

	@Test
	@DisplayName("Test set country ok")
	public void setCountryCodeOkTest() {
		final UserDetails userDetails = UserDetails.builder()
		                                           .countryCode("es")
		                                           .build();
		assertEquals(userDetails.getCountryCode(), "es");
	}

	@Test
	@DisplayName("Test set country wrong")
	public void setCountryCodeWrongTest() {
		assertThrows(DomainException.class, () -> UserDetails.builder()
		                                                     .countryCode("wrong")
		                                                     .build());
	}

	@Test
	@DisplayName("Test set email ok")
	public void emailOkTest() {
		final UserDetails userDetails = UserDetails.builder()
		                                           .email("example@example.com")
		                                           .build();
		assertEquals(userDetails.getEmail(), "example@example.com");
	}

	@Test
	@DisplayName("Test set email wrong")
	public void emailWrongTest() {
		DomainException exception = assertThrows(DomainException.class, () -> UserDetails.builder()
		                                                                                 .email("wrong")
		                                                                                 .build());
		assertEquals(exception.getCode(), DomainExceptionCode.EMAIL_NOT_VALID);
	}

	@Test
	@DisplayName("Test set image url ok")
	public void imageUrlOkTest() {
		final UserDetails userDetails = UserDetails.builder()
		                                           .imageUrl("http://google.com")
		                                           .build();
		assertEquals(userDetails.getImageUrl(), "http://google.com");
	}

	@Test
	@DisplayName("Test set image url wrong")
	public void imageUrlWrongTest() {
		DomainException exception = assertThrows(DomainException.class, () -> UserDetails.builder()
		                                                                                 .imageUrl("wrong")
		                                                                                 .build());
		assertEquals(exception.getCode(), DomainExceptionCode.URL_NOT_VALID);
	}

	@Test
	@DisplayName("Test set language ok")
	public void languageOkTest() {
		final UserDetails userDetails = UserDetails.builder()
		                                           .language("es")
		                                           .build();
		assertEquals(userDetails.getLanguage(), "es");
	}

	@Test
	@DisplayName("Test set language wrong")
	public void languageWrongTest() {
		DomainException exception = assertThrows(DomainException.class, () -> UserDetails.builder()
		                                                                                 .language("wrong")
		                                                                                 .build());
		assertEquals(exception.getCode(), DomainExceptionCode.LANGUAGE_NOT_VALID);
	}
}
