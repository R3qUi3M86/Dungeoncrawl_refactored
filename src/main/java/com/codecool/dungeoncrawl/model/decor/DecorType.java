package com.codecool.dungeoncrawl.model.decor;

public enum DecorType {
    SPIDER_WEB("spiderweb"),
    SHRINE("shrine");;

    private final String tileName;

    DecorType(String tileName) {
        this.tileName = tileName;
    }

    @Override
    public String toString() {
        return tileName;
    }
}
