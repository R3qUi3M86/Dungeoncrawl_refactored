package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.model.Cell;

public class Sword extends Item{
    int damageModifier = 5;
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }


    public int getDamageModifier() {
        return damageModifier;
    }

}
