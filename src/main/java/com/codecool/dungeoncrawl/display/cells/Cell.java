package com.codecool.dungeoncrawl.display.cells;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.map.GameMap;

public class Cell implements Drawable {
    private CellType type;
    private CellImage imageType = CellImage.EMPTY;
    private final int x;
    private final int y;
    private boolean exit = false;
    private Door door;
    private final int rndImgID = (int) (Math.random() * 8);

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

    public void setForestImageType(CellImage imageType){
        if (imageType == CellImage.WALL){
            switch (rndImgID){
                case 0 -> this.imageType = CellImage.TREE1;
                case 1 -> this.imageType = CellImage.TREE2;
                case 2 -> this.imageType = CellImage.TREE3;
                case 3 -> this.imageType = CellImage.TREE4;
                case 4 -> this.imageType = CellImage.TREE5;
                case 5 -> this.imageType = CellImage.TREE6;
                case 6 -> this.imageType = CellImage.TREE7;
                case 7 -> this.imageType = CellImage.TREE8;
            }
        } else if (imageType == CellImage.FLOOR) {
            switch (rndImgID){
                case 0, 4 -> this.imageType = CellImage.WEED1;
                case 1, 5 -> this.imageType = CellImage.WEED2;
                case 2, 6 -> this.imageType = CellImage.WEED3;
                case 3, 7 -> this.imageType = CellImage.WEED4;
            }
        } else {
            this.imageType = imageType;
        }
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

    public Door getDoor() {
        return door;
    }

    public int getRndImgID() {
        return rndImgID;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
