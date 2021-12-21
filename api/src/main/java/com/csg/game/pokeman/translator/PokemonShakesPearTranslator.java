package com.csg.game.pokeman.translator;

import com.csg.game.pokeman.client.TransalationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PokemonShakesPearTranslator implements PokemonTranslator{

    @Autowired
    private TransalationClient transalationClient;

    @Override
    public String translate(String text) {
        return transalationClient.getTranslatedText("yoda", text);
    }
}
