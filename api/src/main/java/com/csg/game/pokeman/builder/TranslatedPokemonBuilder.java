package com.csg.game.pokeman.builder;


import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.translator.PokemonTranslationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslatedPokemonBuilder extends PokemonBuilder {
    @Autowired
    private PokemonTranslationFactory pokemonTranslationFactory;

    public Pokemon buildPokemon(String pokemonName){
        Pokemon pokemon = super.buildPokemon(pokemonName);
        String translate = pokemonTranslationFactory.getPokemonTranslator(pokemon).translate(pokemon.getDescription());
        pokemon.setDescription(translate);
        return pokemon;
    }
}
