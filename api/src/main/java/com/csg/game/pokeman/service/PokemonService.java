package com.csg.game.pokeman.service;


import com.csg.game.pokeman.builder.PokemonBuilderFactory;
import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.model.PokemonType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonBuilderFactory pokemonBuilderFactory;

    public PokemonService(PokemonBuilderFactory pokemonBuilderFactory){
        this.pokemonBuilderFactory = pokemonBuilderFactory;
    }
    public Optional<Pokemon> getPokemon(String pokemonName, PokemonType type) {
        return pokemonBuilderFactory.getPokemonBuilder(type).buildPokemon(pokemonName);
    }
}
