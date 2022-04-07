package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class Key extends Item{

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "key";
    }

}
