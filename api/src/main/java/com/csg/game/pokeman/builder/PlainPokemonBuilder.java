package com.csg.game.pokeman.builder;

import com.csg.game.pokeman.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PlainPokemonBuilder extends PokemonBuilder {
    public Pokemon buildPokemon(String pokemonName){
        return super.buildPokemon(pokemonName);
    }
}

