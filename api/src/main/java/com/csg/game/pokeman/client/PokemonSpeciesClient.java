package com.csg.game.pokeman.client;

import com.csg.game.pokeman.schema.response.PokemonSpeciesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PokemonSpeciesClient extends BaseClient{
    @Value("${pokemon.api.url}")
    private String pokemonServiceUrl;

    public PokemonSpeciesResponse get(int pokemonId) {
        try {
            HttpEntity<String> httpEntity = getHttpEntity();
            String url = pokemonServiceUrl + "/pokemon-species/" + pokemonId;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return objectMapper.readValue(responseEntity.getBody(), PokemonSpeciesResponse.class);
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("Error occurred while fetching Pokemon-Species", e);
        }
        return null;
    }
}
