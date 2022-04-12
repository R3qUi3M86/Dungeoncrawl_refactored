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
    private final int horizontalViewRange = 25;
    private final int verticalViewRange = 21;
    private final Canvas canvas = new Canvas(horizontalViewRange * Tile.TILE_WIDTH, verticalViewRange * Tile.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();



    public void drawMap(GameMap map, Actor[][] actorMatrix, Item[][] itemMatrix, Decor[][] decorMatrix, Camera camera){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < horizontalViewRange; x++) {
            for (int y = 0; y < verticalViewRange; y++) {
                CameraField cameraField = camera.getMatrixInView()[x][y];
                int mapX = cameraField.getX();
                int mapY = cameraField.getY();
                Cell cell = map.getCell(mapX, mapY);
                if (actorMatrix[mapX][mapY] != null) {
                    ViewController.getInstance().drawTile(context, actorMatrix[mapX][mapY], x, y);
                } else if (itemMatrix[mapX][mapY] != null) {
                    ViewController.getInstance().drawTile(context, itemMatrix[mapX][mapY], x, y);
                } else if (decorMatrix[mapX][mapY] != null) {
                    ViewController.getInstance().drawTile(context, decorMatrix[mapX][mapY], x, y);
                } else {
                    ViewController.getInstance().drawTile(context, cell, x, y);
                }
            }
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getHorizontalViewRange() {
        return horizontalViewRange;
    }

    public int getVerticalViewRange() {
        return verticalViewRange;
    }
}
