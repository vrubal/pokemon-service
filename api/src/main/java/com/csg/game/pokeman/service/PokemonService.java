package com.csg.game.pokeman.service;


import com.csg.game.pokeman.builder.PokemonBuilder;
import com.csg.game.pokeman.client.PokemonSpeciesClient;
import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.schema.response.PokemonSpeciesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

   @Autowired
   private PokemonBuilder plainPokemonBuilder;
   @Autowired
   private PokemonSpeciesClient pokemonSpeciesClient;

    public Pokemon getPokemon(String pokemonName){
        return plainPokemonBuilder.buildPokemon(pokemonName);
    }

    public PokemonSpeciesResponse getPokemonSpecies(int pokemonId){
        return pokemonSpeciesClient.get(pokemonId);
    }
/*
    public PokemonHabitat getPokemonHabitat(int id) {
        return pokemonHabitatClient.get(id);
    }*/
}
