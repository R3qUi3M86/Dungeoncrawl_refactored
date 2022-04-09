package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.actors.Player;

public abstract class Decor implements Drawable {
    boolean consumable = false;

    private Cell cell;

    public Decor(Cell cell) {
        this.cell = cell;
    }

    public Decor() {}

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void useDecor(Player player) {}

    public boolean isConsumable() {
        return consumable;
    }

    void setConsumable() {
        this.consumable = true;
    }
}
