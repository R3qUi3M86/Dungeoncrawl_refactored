package com.codecool.dungeoncrawl.model;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    CLOSED_DOOR("closed door"),
    OPENED_DOOR("opened door"),
    STAIRS_UP("stairs up"),
    STAIRS_DOWN("stairs down");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
