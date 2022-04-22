package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.Drawable;

import java.io.Serializable;

public abstract class Decor implements Drawable, Serializable {
    private Cell cell;
    private boolean used = false;

    public Decor(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public abstract DecorType getDecorType();

    public void updateCell() {
        this.cell = GameController.getInstance().getMap().getCell(getX(), getY());
    }
}
