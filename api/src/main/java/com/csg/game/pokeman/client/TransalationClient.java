package com.csg.game.pokeman.client;

import com.csg.game.pokeman.schema.response.TranslationRespose;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Log4j2
@Component
public class TransalationClient extends BaseClient{

    @Value("${translation.api.url}")
    private String translationServiceUrl;

    public Optional<TranslationRespose> getTranslatedText(String type, String plainText) {
        Optional<TranslationRespose> translationResposeOp = Optional.empty();
        try {
            HttpEntity<String> httpEntity = getHttpEntity();
            String url = translationServiceUrl + "/"+type+"?text={encodedText}";
            String encodedText = encodeValue(plainText);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, encodedText);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                log.info("Successfully translated text pokemon with status:{}", responseEntity.getStatusCodeValue());
                translationResposeOp = Optional.of(objectMapper.readValue(responseEntity.getBody(), TranslationRespose.class));
            } else{
                log.error("API call to fetch pokemon is failed with statusL:{} response:{}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
            }
        }
        catch (Exception e) {
            log.error("Failed to translate text", e);
        }
        return translationResposeOp;
    }
    private String encodeValue(String value) {
        return UriUtils.encode(value, StandardCharsets.UTF_8);
    }
}
