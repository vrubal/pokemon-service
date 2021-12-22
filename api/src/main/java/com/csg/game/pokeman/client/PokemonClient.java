package com.csg.game.pokeman.client;

import com.csg.game.pokeman.schema.response.PokemonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Log4j2
public class PokemonClient extends BaseClient{

    @Value("${pokemon.api.url}")
    private String pokemonServiceUrl;

    public Optional<PokemonResponse> get(String pokemonName) {
        Optional<PokemonResponse> pokemonResponseOp = Optional.empty();
        try {
            HttpEntity<String> httpEntity = getHttpEntity();
            String url = pokemonServiceUrl + "/pokemon/" + pokemonName;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                log.info("Successfully fetched pokemon with status:{}", responseEntity.getStatusCodeValue());
                pokemonResponseOp = Optional.of(objectMapper.readValue(responseEntity.getBody(), PokemonResponse.class));
            }else{
                log.error("API call to fetch pokemon is failed with statusL:{} response:{}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
            }
        }catch (Exception e){
            log.error("Error occured while fetching Pokemon", e);
        }
        return pokemonResponseOp;
    }
}
