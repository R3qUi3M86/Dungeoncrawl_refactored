package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.controller.gameSubcontrollers.ButtonsController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.UserInputController;
import com.codecool.dungeoncrawl.model.GameMap;
import com.codecool.dungeoncrawl.model.MapLoader;
import com.codecool.dungeoncrawl.model.actors.Player;
import javafx.scene.Scene;

public class GameController {
    private static GameController gameController;
    private static ViewController viewController;
    private final UserInputController userInputController;
    private ButtonsController buttonsController;
    public static int currentMapNumber = 0;
    private GameMap map;
    private Player player;

    private GameController(){
        viewController = ViewController.getInstance();
        userInputController = new UserInputController();
        this.buttonsController = new ButtonsController(
                viewController.getPlayerGUI().getPickUpButton(),
                viewController.getPlayerGUI().getUseItButton(),
                viewController.getPlayerGUI().getEnterButton());
    }

    public static GameController getInstance(){
        if (gameController == null){
            gameController = new GameController();
        }
        return gameController;
    }

    public void initGame(){
        map = MapLoader.loadMap(currentMapNumber);
        player = new Player(map.getPlayerStartingCell());
    }

    public void addPlayerInputListeners(Scene scene) {
        scene.setOnKeyPressed(userInputController::onKeyPressed); //find way to remove key pressed listeners after player dead
        buttonsController.setGUIButtonsEventHandlers();
    }

    public void startGame(){
        addPlayerInputListeners(viewController.getCurrentScene());
        viewController.refresh(map, player);
    }

    public void playTurn(){
        buttonsController.setPlayerGUIButtons(player);
        map.moveActors();
        map.getPlayer().resolveEffects();
        map.cleanUp();
        viewController.refresh(map, player);
    }

    public void setNextMap(){
        currentMapNumber++;
        map = MapLoader.loadMap(currentMapNumber);
    }

    public void travelToNextLevel(){
        // Setup next map
        buttonsController.setPlayerGUIButtons(player);
    }

    public Player getPlayer(){
        return player;
    }
}
