package com.csg.game.pokeman.builder;

import com.csg.game.pokeman.client.PokemonClient;
import com.csg.game.pokeman.client.PokemonSpeciesClient;
import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.schema.response.PokemonResponse;
import com.csg.game.pokeman.schema.response.PokemonSpeciesResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public abstract class PokemonBuilder {
    @Autowired
    private PokemonClient pokemonClient;
    @Autowired
    private PokemonSpeciesClient pokemonSpeciesClient;

    public Optional<Pokemon> buildPokemon(String pokemonName) {
        Optional<PokemonResponse> pokemonResponse = pokemonClient.get(pokemonName);
        if(pokemonResponse.isPresent()) {
            Optional<PokemonSpeciesResponse> pokemonSpeciesResponse = pokemonSpeciesClient.get(pokemonResponse.get().getId());
            return translatePokemon(pokemonResponse, pokemonSpeciesResponse);
        }
        return Optional.empty();
    }

    private Optional<Pokemon> translatePokemon(Optional<PokemonResponse> pokemonResponse, Optional<PokemonSpeciesResponse> pokemonSpeciesResponse){
        Optional<Pokemon> pokemonOptional = Optional.empty();
        if(pokemonResponse.isPresent()){
            Pokemon pokemon = new Pokemon();
            pokemon.setId(pokemonResponse.get().getId());
            pokemon.setName(pokemonResponse.get().getName());
            pokemonOptional=Optional.of(pokemon);
        }
        if(pokemonSpeciesResponse.isPresent()){
            pokemonOptional.get().setHabitat(pokemonSpeciesResponse.get().getHabitat().getName());
            pokemonOptional.get().setLegendary(pokemonSpeciesResponse.get().isLegendary());
            if(pokemonSpeciesResponse.get().getFlavorTexts().isPresent()){
                pokemonOptional.get().setDescription(pokemonSpeciesResponse.get().getFlavorTexts().get().get(0).getFlavorText());
            }
        }
        return pokemonOptional;
    }

}
