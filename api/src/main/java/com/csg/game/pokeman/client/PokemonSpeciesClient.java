package com.csg.game.pokeman.client;

import com.csg.game.pokeman.schema.response.PokemonSpeciesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
public class PokemonSpeciesClient extends BaseClient{
    @Value("${pokemon.api.url}")
    private String pokemonServiceUrl;

    public Optional<PokemonSpeciesResponse> get(int pokemonId) {
        Optional<PokemonSpeciesResponse> pokemonSpeciesResponseOp = Optional.empty();
        try {
            HttpEntity<String> httpEntity = getHttpEntity();
            String url = pokemonServiceUrl + "/pokemon-species/" + pokemonId;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                log.info("Successfully fetched pokemon-species with status:{}", responseEntity.getStatusCodeValue());
                pokemonSpeciesResponseOp = Optional.of(objectMapper.readValue(responseEntity.getBody(), PokemonSpeciesResponse.class));
            }else{
                log.error("API call to fetch pokemon-species is failed with statusL:{} response:{}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
            }
        }catch (Exception e){
            log.error("Error occurred while fetching Pokemon-Species", e);
        }
        return pokemonSpeciesResponseOp;
    }
}
