package com.csg.game.pokeman.translator;

import com.csg.game.pokeman.client.TransalationClient;
import com.csg.game.pokeman.schema.response.TranslationRespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PokemonYodaTranslator implements PokemonTranslator{

    @Autowired
    private TransalationClient transalationClient;

    @Override
    public String translate(String text) {
        Optional<TranslationRespose> translationResposeOp = transalationClient.getTranslatedText(TranslationType.YODA.type, text);
        if(translationResposeOp.isPresent()){
            String translated = translationResposeOp.get().getContents().getTranslated();
            return decodeTranslatedText(translated);
        }else{
            return text;
        }
    }
}
