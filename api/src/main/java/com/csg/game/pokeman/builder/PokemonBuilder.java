package com.csg.game.pokeman.builder;

import com.csg.game.pokeman.client.PokemonClient;
import com.csg.game.pokeman.client.PokemonSpeciesClient;
import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.schema.response.PokemonResponse;
import com.csg.game.pokeman.schema.response.PokemonSpeciesResponse;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class PokemonBuilder {
    @Autowired
    private PokemonClient pokemonClient;
    @Autowired
    private PokemonSpeciesClient pokemonSpeciesClient;

    public Pokemon buildPokemon(String pokemonName){
        PokemonResponse pokemonResponse = pokemonClient.get(pokemonName);
        PokemonSpeciesResponse pokemonSpeciesResponse = pokemonSpeciesClient.get(pokemonResponse.getId());
        return translatePokemon(pokemonResponse, pokemonSpeciesResponse);
    }

    private Pokemon translatePokemon(PokemonResponse pokemonResponse, PokemonSpeciesResponse pokemonSpeciesResponse){
        Pokemon pokemon = new Pokemon();
        if(pokemonResponse != null){
            pokemon.setId(pokemonResponse.getId());
            pokemon.setName(pokemonResponse.getName());
        }
        if(pokemonSpeciesResponse != null){
            pokemon.setHabitat(pokemonSpeciesResponse.getHabitat().getName());
            pokemon.setLegendary(pokemonSpeciesResponse.isLegendary());
            if(pokemonSpeciesResponse.getFlavorTexts().size()>0){
                pokemon.setDescription(pokemonSpeciesResponse.getFlavorTexts().get(0).getFlavorText());
            }
        }
        return pokemon;
    }

}
