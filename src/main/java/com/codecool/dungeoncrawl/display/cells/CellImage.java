package com.codecool.dungeoncrawl.display.cells;

public enum CellImage {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    CLOSED_DOOR("closed door"),
    OPENED_DOOR("opened door"),
    STAIRS_UP("stairs up"),
    STAIRS_DOWN("stairs down");


    private final String tileName;

    CellImage(String tileName) {
        this.tileName = tileName;
    }

    public String getName() {
        return tileName;
    }
}
