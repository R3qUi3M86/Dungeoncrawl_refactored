package com.codecool.dungeoncrawl.display.cells;

public enum DoorType {
    GOLDEN_DOOR("golden door"), BLUE_DOOR("blue door"), RED_DOOR("red door");

    private final String tileName;

    DoorType(String tileName) {
        this.tileName = tileName;
    }

    public String getName() {
        return tileName;
    }
}
