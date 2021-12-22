package com.csg.game.pokeman.builder;

import com.csg.game.pokeman.model.PokemonType;
import org.springframework.stereotype.Component;

@Component
public class PokemonBuilderFactory {
    private final PlainPokemonBuilder plainPokemonBuilder;
    private final TranslatedPokemonBuilder translatedPokemonBuilder;

    public PokemonBuilderFactory(PlainPokemonBuilder plainPokemonBuilder,
                                 TranslatedPokemonBuilder translatedPokemonBuilder){
        this.plainPokemonBuilder = plainPokemonBuilder;
        this.translatedPokemonBuilder = translatedPokemonBuilder;
    }

    public PokemonBuilder getPokemonBuilder(PokemonType type){
        switch (type) {
            case PLAIN:
                return plainPokemonBuilder;
            case TRANSLATED:
                return translatedPokemonBuilder;
            default:
                return null;
        }
    }
}
