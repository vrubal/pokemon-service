package com.csg.game.pokeman.client;

import com.csg.game.pokeman.schema.response.PokemonHabitat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PokemonHabitatClient extends  BaseClient{
    @Value("${pokemon.api.url}")
    private String pokemonServiceUrl;

    public PokemonHabitat get(int pokemonId) {
        try {
            HttpEntity<String> httpEntity = getHttpEntity();
            String url = pokemonServiceUrl + "/pokemon-habitat/" + pokemonId;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return objectMapper.readValue(responseEntity.getBody(), PokemonHabitat.class);
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("Error occurred while fetching Pokemon-Species", e);
        }
        return null;
    }
}
