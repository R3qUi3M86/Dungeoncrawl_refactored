package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.DoorType;

public class GoldenKey extends Item implements Key {

    private DoorType matchingDoor = DoorType.GOLDEN_DOOR;

    public GoldenKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "golden key";
    }

    public DoorType getMatchingDoor() {
        return matchingDoor;
    }
}
