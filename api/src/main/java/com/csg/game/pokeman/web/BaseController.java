package com.csg.game.pokeman.web;

import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    public <T> ResponseEntity<T> getResponse(T t){
        return ResponseEntity.ok(t);
    }
}
