package com.csg.game.pokeman.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class BaseClient {
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    protected ObjectMapper objectMapper;

    protected HttpEntity<String> getHttpEntity(){
        HttpEntity<String> httpEntity = new HttpEntity(getHttpHeaders());
        return httpEntity;
    }
    protected HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
        return httpHeaders;
    }
}
