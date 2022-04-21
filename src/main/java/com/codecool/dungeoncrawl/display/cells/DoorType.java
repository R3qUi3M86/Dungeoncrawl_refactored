package com.codecool.dungeoncrawl.display.cells;

import java.io.Serializable;

public enum DoorType implements Serializable {
    GOLDEN_DOOR("golden door"), BLUE_DOOR("blue door"), RED_DOOR("red door");

    private final String tileName;

    DoorType(String tileName) {
        this.tileName = tileName;
    }

    public String getName() {
        return tileName;
    }
}
