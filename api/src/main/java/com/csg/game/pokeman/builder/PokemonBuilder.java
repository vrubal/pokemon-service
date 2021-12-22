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

    private Optional<Pokemon> translatePokemon(Optional<PokemonResponse> pokemonResponseOp, Optional<PokemonSpeciesResponse> pokemonSpeciesResponseOp){
        Optional<Pokemon> pokemonOptional = Optional.empty();
        if(pokemonResponseOp.isPresent()){
            PokemonResponse pokemonResponse = pokemonResponseOp.get();
            Pokemon pokemon = new Pokemon();
            pokemon.setId(pokemonResponse.getId());
            pokemon.setName(pokemonResponse.getName());
            pokemonOptional=Optional.of(pokemon);
        }
        if(pokemonSpeciesResponseOp.isPresent()){
            Pokemon pokemon = pokemonOptional.get();
            PokemonSpeciesResponse pokemonSpeciesResponse = pokemonSpeciesResponseOp.get();
            pokemon.setLegendary(pokemonSpeciesResponse.isLegendary());
            if(null != pokemonSpeciesResponse.getFlavorTexts() && pokemonSpeciesResponse.getFlavorTexts().size() > 0){
                pokemon.setDescription(pokemonSpeciesResponse.getFlavorTexts().get(0).getFlavorText());
            }
            if(null != pokemonSpeciesResponse.getHabitat()) {
                pokemon.setHabitat(pokemonSpeciesResponse.getHabitat().getName());
            }
        }
        return pokemonOptional;
    }

}
