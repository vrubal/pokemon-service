package com.csg.game.pokeman.translator;

public enum TranslationType {
    YODA("yoda.json"), SHAKESPEARE("shakespeare.json");
    String type;
    TranslationType(String type) {
        this.type = type;
    }
}
