package com.csg.game.pokeman.client;

import com.csg.game.pokeman.schema.response.PokemonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PokemonClient extends BaseClient{

    @Value("${pokemon.api.url}")
    private String pokemonServiceUrl;

    public PokemonResponse get(String pokemonName) {
        try {
            HttpEntity<String> httpEntity = getHttpEntity();
            String url = pokemonServiceUrl + "/pokemon/" + pokemonName;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return objectMapper.readValue(responseEntity.getBody(), PokemonResponse.class);
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("Error occured while fetching Pokemon", e);
        }
        return null;
    }
}
