package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.controller.ViewController;
import com.codecool.dungeoncrawl.display.tiles.Tile;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapGUI {
    private final Canvas canvas = new Canvas(25 * Tile.TILE_WIDTH, 20 * Tile.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();

    public void drawMap(GameMap map, Actor[][] actorMatrix, Item[][] itemMatrix, Decor[][] decorMatrix){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (actorMatrix[x][y] != null) {
                    ViewController.getInstance().drawTile(context, actorMatrix[x][y], x, y);
                } else if (itemMatrix[x][y] != null) {
                    ViewController.getInstance().drawTile(context, itemMatrix[x][y], x, y);
                } else if (decorMatrix[x][y] != null) {
                    ViewController.getInstance().drawTile(context, decorMatrix[x][y], x, y);
                } else {
                    ViewController.getInstance().drawTile(context, cell, x, y);
                }
            }
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
