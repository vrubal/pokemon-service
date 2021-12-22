package com.csg.game.pokeman.builder;

import com.csg.game.pokeman.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlainPokemonBuilder extends PokemonBuilder {
    public Optional<Pokemon> buildPokemon(String pokemonName) {
        return super.buildPokemon(pokemonName);
    }
}

