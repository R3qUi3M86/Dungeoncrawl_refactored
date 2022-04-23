package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class Shield extends Item implements Armor{
    private final int armorVal = 2;

    public Shield(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "shield";
    }

    @Override
    public int getArmorValue() {
        return armorVal;
    }
}
