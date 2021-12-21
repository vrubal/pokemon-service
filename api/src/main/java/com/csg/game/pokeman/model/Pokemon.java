package com.csg.game.pokeman.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pokemon {
    private int id;
    private String name;
    private String description;
    private boolean isLegendary;
    private String habitat;
}
