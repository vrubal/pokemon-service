package com.csg.game.pokeman.translator;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DefaultPokemonTranslator implements PokemonTranslator{

    @Override
    public String translate(String text) {
        return text;
    }
}
