package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.actors.Player;

import java.io.Serializable;

public abstract class Item implements Drawable, Serializable {
    boolean consumable = false;

    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
    }

    public Item() {}

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void useItem(Player player) {}

    public boolean isConsumable() {
        return consumable;
    }

    void setConsumable() {
        this.consumable = true;
    }
}
