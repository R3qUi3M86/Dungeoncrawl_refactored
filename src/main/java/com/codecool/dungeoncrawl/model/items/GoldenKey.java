package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class GoldenKey extends Item{

    public GoldenKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "key";
    }

}
