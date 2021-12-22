package com.csg.game.pokeman.translator;

import com.csg.game.pokeman.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PokemonTranslationFactory {
    @Autowired
    private PokemonYodaTranslator pokemonYodaTranslator;
    @Autowired
    private PokemonShakesPearTranslator pokemonShakesPearTranslator;
    @Autowired
    private DefaultPokemonTranslator defaultPokemonTranslator;

    public PokemonTranslator getPokemonTranslator(Pokemon pokemon){
        if(pokemon.getHabitat()==null)
            return defaultPokemonTranslator;

        if ("cave".equalsIgnoreCase(pokemon.getHabitat()))
            return pokemonYodaTranslator;
        else
            return pokemonShakesPearTranslator;

    }
}
