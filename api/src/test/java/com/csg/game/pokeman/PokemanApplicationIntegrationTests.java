package com.csg.game.pokeman;

import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.web.PokemanController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PokemanApplicationIntegrationTests {

	@Autowired
	private PokemanController controller;

	@Test
	void testSuccessFullFetch() {
		ResponseEntity<Pokemon> pokemonOp = controller.getPokemon("mewtwo");
		Assertions.assertTrue(pokemonOp.getStatusCode().is2xxSuccessful());
	}
	@Test
	void testFailedFetch() {
		ResponseEntity<Pokemon> pokemonOp = controller.getPokemon("random");
		Assertions.assertTrue(pokemonOp.getStatusCode().is4xxClientError());
	}
	@Test
	void testSuccessFulFetchTranslated() {
		ResponseEntity<Pokemon> pokemonOp = controller.getTranlatedPokemon("mewtwo");
		Assertions.assertTrue(pokemonOp.getStatusCode().is2xxSuccessful());
	}
}
