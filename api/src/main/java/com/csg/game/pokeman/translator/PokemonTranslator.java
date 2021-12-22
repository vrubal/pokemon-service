package com.csg.game.pokeman.translator;

import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

public interface PokemonTranslator {
    String translate(String text);
    default String decodeTranslatedText(String translatedText){
        return UriUtils.decode(translatedText, StandardCharsets.UTF_8);
    }
}
