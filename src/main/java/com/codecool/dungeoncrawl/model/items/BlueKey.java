package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.DoorType;

public class BlueKey extends Item implements Key {
    private DoorType matchingDoor = DoorType.BLUE_DOOR;

    public BlueKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "blue key";
    }

    public DoorType getMatchingDoor() {
        return matchingDoor;
    }
}
