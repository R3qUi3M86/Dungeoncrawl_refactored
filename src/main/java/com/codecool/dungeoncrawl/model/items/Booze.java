package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.actors.Player;

public class Booze extends Item{
    public Booze(Cell cell) {
        super(cell);
        setConsumable();
    }

    @Override
    public String getTileName() {
        return "booze";
    }

    @Override
    public void useItem(Player player) {
        player.getWasted();
    }
}
