package com.csg.game.pokeman.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranslationRespose {
    @JsonProperty("contents")
    private Contents contents;
}
