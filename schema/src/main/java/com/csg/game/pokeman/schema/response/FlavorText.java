package com.csg.game.pokeman.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlavorText {
    @JsonProperty("flavor_text")
    private String flavorText;
    private Language language;
}
