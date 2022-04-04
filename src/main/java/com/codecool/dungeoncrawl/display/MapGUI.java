package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.controller.ViewController;
import com.codecool.dungeoncrawl.display.tiles.Tile;
import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.GameMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapGUI {
    private final Canvas canvas = new Canvas(25 * Tile.TILE_WIDTH, 20 * Tile.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();

    public void drawMap(GameMap map){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    ViewController.getInstance().drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    ViewController.getInstance().drawTile(context, cell.getItem(), x, y);
                } else if (cell.getDecor() != null) {
                    ViewController.getInstance().drawTile(context, cell.getDecor(), x, y);
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
