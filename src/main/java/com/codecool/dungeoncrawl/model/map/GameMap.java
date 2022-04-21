package com.codecool.dungeoncrawl.model.map;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellRenderType;
import com.codecool.dungeoncrawl.display.cells.CellType;

import java.io.Serializable;

public class GameMap implements Serializable {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private int[] playerStartingPosition;
    private final CellRenderType renderType;

    public GameMap(int width, int height, CellType defaultCellType, CellRenderType renderType) {
        this.width = width;
        this.height = height;
        this.renderType = renderType;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Cell getPlayerStartingCell(){
        return cells[playerStartingPosition[0]][playerStartingPosition[1]];
    }

    public CellRenderType getRenderType() {
        return renderType;
    }

    public void setPlayerStartingPosition(int[] playerStartingPosition) {
        this.playerStartingPosition = playerStartingPosition;
    }
}
