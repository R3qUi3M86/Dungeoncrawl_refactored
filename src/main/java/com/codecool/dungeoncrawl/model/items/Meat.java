package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.actors.Player;

public class Meat extends Item{
    private final int heal = 5;

    public Meat(Cell cell) {
        super(cell);
        setConsumable();
    }

    @Override
    public String getTileName() {
        return "meat";
    }

    @Override
    public void useItem(Player player) {
        player.eatFood(heal);
    }
}
