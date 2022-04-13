package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.items.GoldenKey;

public class Demon extends Warlock{
    public Demon(Cell cell){
        super(cell);
        attack = 4;
        health = 50;
        minionLimit = 5;
        spawnMinionTimerLimit = 4;
    }

    @Override
    public String getCellImageName() {
        return "demon";
    }

    @Override
    void killActor() {
        super.killActor();
        GameController.getInstance().getItemController().getItemMatrix()[this.getX()][this.getY()] = new GoldenKey(this.getCell());
    }
}
