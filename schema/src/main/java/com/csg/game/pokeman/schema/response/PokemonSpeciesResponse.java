package com.csg.game.pokeman.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PokemonSpeciesResponse {
    @JsonProperty("is_legendary")
    private boolean isLegendary;
    @JsonProperty("flavor_text_entries")
    private List<FlavorText> flavorTexts;
    @JsonProperty("form_descriptions")
    private List<FormDescription> formDescriptions;
    @JsonProperty("habitat")
    private PokemonHabitat habitat;
}
