package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.display.Camera;
import com.codecool.dungeoncrawl.display.MapGUI;
import com.codecool.dungeoncrawl.display.PlayerGUI;
import com.codecool.dungeoncrawl.display.tiles.Tile;
import com.codecool.dungeoncrawl.display.tiles.Tiles;
import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;
import com.codecool.dungeoncrawl.model.actors.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ViewController {
    private static ViewController viewController;
    private static GameController gameController;
    private Camera camera;
    private final MapGUI mapGUI;
    private final PlayerGUI playerGUI;
    private Scene currentScene;
    BorderPane borderPane;

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
        borderPane = new BorderPane();
        currentScene = new Scene(borderPane);
        borderPane.setCenter(mapGUI.getCanvas());
        borderPane.setRight(playerGUI.getUi());

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.setScene(currentScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void refresh(GameMap gameMap, Player player) {
        refreshPlayerGUI(player);
        refreshMapGUI(gameMap);
        borderPane.requestFocus();
    }

    private void refreshMapGUI(GameMap gameMap){
        Actor[][] actorMatrix = GameController.getInstance().getActorController().getActorMatrix();
        Item[][] itemMatrix = GameController.getInstance().getItemController().getItemMatrix();
        Decor[][] decorMatrix = GameController.getInstance().getDecorController().getDecorMatrix();
        camera.moveAsRequired(GameController.getInstance().getPlayer());
        mapGUI.drawMap(gameMap, actorMatrix, itemMatrix, decorMatrix, camera);
    }

    private void refreshPlayerGUI(Player player){
        playerGUI.drawPlayerGui(player);
    }

    public void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = Tiles.getTileMap().get(d.getCellImageName()); // get dungeon/forest tile map (based on map CEll render type)
        context.drawImage(Tiles.getTileset(), tile.x, tile.y, tile.w, tile.h,
                x * Tile.TILE_WIDTH, y * Tile.TILE_WIDTH, Tile.TILE_WIDTH, Tile.TILE_WIDTH);
    }

    public void setCamera() {
        GameMap map = GameController.getInstance().getMap();
        int[] targetField = new int[]{map.getPlayerStartingCell().getX(), map.getPlayerStartingCell().getY()};
        int hRange = mapGUI.getHorizontalViewRange();
        int vRange = mapGUI.getVerticalViewRange();
        this.camera = new Camera(map, targetField, hRange, vRange);
        camera.followPlayer(GameController.getInstance().getPlayer());
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public PlayerGUI getPlayerGUI() {
        return playerGUI;
    }
}
