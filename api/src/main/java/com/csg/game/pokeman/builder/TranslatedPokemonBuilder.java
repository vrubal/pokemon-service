package com.csg.game.pokeman.builder;


import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.translator.PokemonTranslationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TranslatedPokemonBuilder extends PokemonBuilder {
    @Autowired
    private PokemonTranslationFactory pokemonTranslationFactory;

    public Optional<Pokemon> buildPokemon(String pokemonName) {
        Optional<Pokemon> pokemonOp = super.buildPokemon(pokemonName);
        if(pokemonOp.isPresent()) {
            String translate = pokemonTranslationFactory.getPokemonTranslator(pokemonOp.get()).translate(pokemonOp.get().getDescription());
            pokemonOp.get().setDescription(translate);
        }
        return pokemonOp;
    }
}
