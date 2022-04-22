package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class Sword extends Item {
    int damageModifier = 5;

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "sword";
    }

    public int getDamageModifier() {
        return damageModifier;
    }

}
