package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class SharpSword extends Item implements Weapon {
    int damageModifier = 12;

    public SharpSword(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "sharp sword";
    }

    public int getDamageModifier() {
        return damageModifier;
    }
}
