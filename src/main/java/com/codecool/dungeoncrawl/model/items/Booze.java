package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.Player;

public class Booze extends Item{
    public Booze(Cell cell) {
        super(cell);
        setConsumable();
    }

    @Override
    public String getCellImageName() {
        return "booze";
    }

    @Override
    public void useItem(Player player) {
        player.getWasted();
        GameController.getInstance().getUserInputController().setDrunkKeyMapping();
    }
}
