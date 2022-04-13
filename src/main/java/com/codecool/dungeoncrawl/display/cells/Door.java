package com.codecool.dungeoncrawl.display.cells;

import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.Key;

public class Door implements Drawable {
    private boolean open = false;
    private final DoorType doorType;

    public Door(DoorType doorType){
        this.doorType = doorType;
    }

    @Override
    public String getCellImageName() {
        if (open){
            return "opened " + doorType.getName();
        } else {
            return "closed " + doorType.getName();
        }
    }

    public boolean keyMatches(Key key) {
        return key.getMatchingDoor() == doorType;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
