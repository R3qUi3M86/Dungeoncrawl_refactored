package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.display.MapGUI;
import com.codecool.dungeoncrawl.display.PlayerGUI;
import com.codecool.dungeoncrawl.display.tiles.Tile;
import com.codecool.dungeoncrawl.display.tiles.Tiles;
import com.codecool.dungeoncrawl.model.Drawable;
import com.codecool.dungeoncrawl.model.GameMap;
import com.codecool.dungeoncrawl.model.actors.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ViewController {
    private static ViewController viewController;
    private static GameController gameController;
    private final MapGUI mapGUI;
    private final PlayerGUI playerGUI;
    private Scene currentScene;

    private ViewController() {
        mapGUI = new MapGUI();
        playerGUI = new PlayerGUI();
        playerGUI.initPlayerGUI();
    }

    public static ViewController getInstance() {
        if (viewController == null) {
            viewController = new ViewController();
            gameController = GameController.getInstance();
        }
        return viewController;
    }

    public void initWindow(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        currentScene = new Scene(borderPane);
        borderPane.setCenter(mapGUI.getCanvas());
        borderPane.setRight(playerGUI.getUi());


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.setScene(currentScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void refresh(GameMap gameMap, Player player) {
        refreshMapGUI(gameMap);
        refreshPlayerGUI(player);
    }

    private void refreshMapGUI(GameMap gameMap){
        mapGUI.drawMap(gameMap);
    }

    private void refreshPlayerGUI(Player player){
        playerGUI.drawPlayerGui(player);
    }

    public void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = Tiles.getTileMap().get(d.getTileName());
        context.drawImage(Tiles.getTileset(), tile.x, tile.y, tile.w, tile.h,
                x * Tile.TILE_WIDTH, y * Tile.TILE_WIDTH, Tile.TILE_WIDTH, Tile.TILE_WIDTH);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public PlayerGUI getPlayerGUI() {
        return playerGUI;
    }
}
