package com.csg.game.pokeman.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransalationClient extends BaseClient{

    @Value("${pokemon.api.url}")
    private String translationServiceUrl;

    public String getTranslatedText(String type, String text) {
        HttpEntity<String> httpEntity = getHttpEntity();
        String url = translationServiceUrl + "/type/";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, text);
        return responseEntity.getBody();
    }
}
