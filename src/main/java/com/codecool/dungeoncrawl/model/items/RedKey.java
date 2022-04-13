package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.DoorType;

public class RedKey extends Item implements Key{
    private DoorType matchingDoor = DoorType.RED_DOOR;

    public RedKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "red key";
    }

    public DoorType getMatchingDoor() {
        return matchingDoor;
    }
}
