package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.controller.gameSubcontrollers.ButtonsController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.ActorController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.DecorController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.ItemController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.UserKeyboardInputController;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Puzzler;
import com.codecool.dungeoncrawl.model.decor.CardPuzzle;
import com.codecool.dungeoncrawl.model.items.GoldenKey;
import com.codecool.dungeoncrawl.model.map.GameMap;
import com.codecool.dungeoncrawl.model.map.MapLoader;
import com.codecool.dungeoncrawl.model.actors.Player;
import javafx.scene.Scene;

import java.util.ArrayList;

public class GameController {
    private static GameController gameController;
    private static ViewController viewController;
    private final ActorController actorController;
    private final DecorController decorController;
    private final ItemController itemController;
    private UserKeyboardInputController userInputController;
    private ButtonsController buttonsController;
    public static int currentMapNumber = 0;
    private GameMap map;
    private Player player;

    private GameController(){
        viewController = ViewController.getInstance();
        this.buttonsController = new ButtonsController(
                viewController.getPlayerGUI().getPickUpButton(),
                viewController.getPlayerGUI().getUseItButton(),
                viewController.getPlayerGUI().getEnterButton());

        //Sub-controllers init
        actorController = new ActorController();
        decorController = new DecorController();
        itemController = new ItemController();
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
        actorController.setPlayer(player);
        itemController.setPlayer(player);
        decorController.setPlayer(player);
        ViewController.getInstance().setCamera();
    }

    public void initGame(Player player){
        map = MapLoader.loadMap(currentMapNumber);
        this.player = player;
        actorController.setPlayer(player);
        itemController.setPlayer(player);
        decorController.setPlayer(player);
        ViewController.getInstance().setCamera();
    }

    public void addPlayerInputListeners(Scene scene) {
        scene.setOnKeyPressed(userInputController::onSoberKeyPressed); //find way to remove key pressed listeners after player dead
        buttonsController.setGUIButtonsEventHandlers();
    }

    public void startGame(){
        this.userInputController = new UserKeyboardInputController(viewController.getCurrentScene());
        addPlayerInputListeners(viewController.getCurrentScene());
        viewController.refresh(map, player);
    }

    public void playTurn(MovementDir movementDir) {
        if (player.isAlive()) {
            actorController.takePlayerTurn(movementDir);
            actorController.takeAllNPCTurn();
            actorController.clearCorpses();
            resolveTimedEffects();
            actorController.clearCorpses();
            buttonsController.setPlayerGUIButtons(player);
            viewController.refresh(map, player);
        }
    }

    private void resolveTimedEffects(){
        actorController.resolveActorTimedEffects();
    }

    private void setNextMap(){
        currentMapNumber++;
        map = MapLoader.loadMap(currentMapNumber);
        ViewController.getInstance().setCamera();
    }

    public void travelToNextLevel(){
        setNextMap();
        player.setCell(map.getPlayerStartingCell());
        actorController.setPlayer(player);
        buttonsController.setPlayerGUIButtons(player);
        ViewController.getInstance().getCamera().followPlayer(player);
        viewController.refresh(map, player);
    }

    public void solvePuzzle(){
        for (Actor actor : actorController.getNpcList()){
            if(actor instanceof Puzzler){
                itemController.addItemToController(actor.getX(), actor.getY(), new GoldenKey(actor.getCell()));
                actorController.getMoveSubcontroller().moveActor(actor, MovementDir.M_RIGHT);
            }
        }
    }

    public void disablePuzzle(ArrayList<CardPuzzle> cardPuzzles){
        for (CardPuzzle card : cardPuzzles){
            card.getCell().setType(CellType.WALKABLE);
        }
    }

    public Player getPlayer(){
        return player;
    }

    public ActorController getActorController() {
        return actorController;
    }

    public DecorController getDecorController() {
        return decorController;
    }

    public ItemController getItemController() {
        return itemController;
    }

    public UserKeyboardInputController getUserInputController() {
        return userInputController;
    }

    public GameMap getMap() {
        return map;
    }
}
