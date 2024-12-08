package br.com.ruanziin.placeservice;

import br.com.ruanziin.placeservice.api.PlaceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlaceServiceApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	public void testCreatedPlaceSucess(){
		final String name = "Valid Name";
		final String city = "Valid City";
		final String state = "Valid State";
		final String slug = "valid-name";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, city, state))
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("name").isEqualTo(name)
				.jsonPath("city").isEqualTo(city)
				.jsonPath("state").isEqualTo(state)
				.jsonPath("slug").isEqualTo(slug)
				.jsonPath("createdAt").isNotEmpty()
				.jsonPath("updatedAt").isNotEmpty();
	}

	@Test
	public void testCreatedPlaceFailure(){
		final String name = "";
		final String city = "";
		final String state = "";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, city, state))
				.exchange()
				.expectStatus().isBadRequest();
	}

}
