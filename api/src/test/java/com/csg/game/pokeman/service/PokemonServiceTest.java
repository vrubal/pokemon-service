package com.csg.game.pokeman.service;

import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.model.PokemonType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PokemonServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PokemonService pokemonService;

    @Test
    public void testGetPokemon(){
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon/mewtwo"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getPokemonResponse()));
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon-species/1234"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getPokemonSpeciesResponse()));
        Optional<Pokemon> pokemon = pokemonService.getPokemon("mewtwo", PokemonType.PLAIN);
        Assertions.assertTrue(pokemon.isPresent());
        Assertions.assertEquals(pokemon.get().getId(), 1234);
    }

    @Test
    public void testGetTranslatedPokemon(){
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon/mewtwo"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getPokemonResponse()));
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon-species/1234"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getPokemonSpeciesResponse()));
        when(restTemplate.exchange(eq("https://api.funtranslations.com/translate/yoda.json?text={encodedText}"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getTranslationResponse()));

        Optional<Pokemon> pokemon = pokemonService.getPokemon("mewtwo", PokemonType.TRANSLATED);
        Assertions.assertTrue(pokemon.isPresent());
        Assertions.assertEquals(pokemon.get().getId(), 1234);
    }

    @Test
    public void testGetTranslatedPokemon_habitat_cave(){
        String speciesRes = "{\"is_legendary\":true, \"habitat\":{\"name\":\"cave\"}, \"flavor_text_entries\":[{\"flavor_text\":\"Description\"}]}";
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon/mewtwo"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getPokemonResponse()));
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon-species/1234"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(speciesRes));
        when(restTemplate.exchange(eq("https://api.funtranslations.com/translate/yoda.json?text={encodedText}"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class), any(String.class))).
                thenReturn(ResponseEntity.ok().body(getTranslationResponse()));

        Optional<Pokemon> pokemon = pokemonService.getPokemon("mewtwo", PokemonType.TRANSLATED);
        Assertions.assertTrue(pokemon.isPresent());
        Assertions.assertEquals(pokemon.get().getId(), 1234);
    }
    @Test
    public void testGetTranslatedPokemon_habitat_water(){
        String speciesRes = "{\"is_legendary\":true, \"habitat\":{\"name\":\"water\"},\"flavor_text_entries\":[{\"flavor_text\":\"Description\"}]}";
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon/mewtwo"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(getPokemonResponse()));
        when(restTemplate.exchange(eq("https://pokeapi.co/api/v2/pokemon-species/1234"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).
                thenReturn(ResponseEntity.ok().body(speciesRes));
        when(restTemplate.exchange(eq("https://api.funtranslations.com/translate/shakespeare.json?text={encodedText}"), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class), any(String.class))).
                thenReturn(ResponseEntity.ok().body(getTranslationResponse()));

        Optional<Pokemon> pokemon = pokemonService.getPokemon("mewtwo", PokemonType.TRANSLATED);
        Assertions.assertTrue(pokemon.isPresent());
        Assertions.assertEquals(pokemon.get().getId(), 1234);
    }

    private String getTranslationResponse() {
        return "{\"contents\":{\"translated\":\"Text\"}}";
    }

    private String getPokemonSpeciesResponse() {
        return "{\"is_legendary\":true, \"flavor_text_entries\":[{\"flavor_text\":\"Description\"}]}";
    }

    private String getPokemonResponse() {
        return "{\"name\":\"mewtwo\", \"id\":1234}";
    }
}