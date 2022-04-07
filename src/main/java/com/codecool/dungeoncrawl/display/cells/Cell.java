package com.codecool.dungeoncrawl.display.cells;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.map.GameMap;

public class Cell implements Drawable {
    private CellType type;
    private CellImage imageType;
    private final int x;
    private final int y;
    private boolean exit = false;

    public Cell(int x, int y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public String getCellImageName() {
        return imageType.getName();
    }

    public CellType getType() {
        return type;
    }

    public CellImage getImageType() {
        return imageType;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setImageType(CellImage imageType){
        this.imageType = imageType;
    }

    public int getX() {
        return x;
    }

    public Cell getNeighboringCell(int dx, int dy) {
        GameMap gameMap = GameController.getInstance().getMap();
        return gameMap.getCell(x + dx, y + dy);
    }

    public int getY() {
        return y;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
