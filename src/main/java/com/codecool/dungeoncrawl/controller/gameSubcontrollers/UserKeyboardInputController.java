package com.codecool.dungeoncrawl.controller.gameSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.database.*;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserKeyboardInputController {
    Scene scene;

    public UserKeyboardInputController(Scene scene){
        this.scene = scene;
    }

    public void onSoberKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> GameController.getInstance().playTurn(MovementDir.M_UP);
            case DOWN -> GameController.getInstance().playTurn(MovementDir.M_DOWN);
            case LEFT -> GameController.getInstance().playTurn(MovementDir.M_LEFT);
            case RIGHT -> GameController.getInstance().playTurn(MovementDir.M_RIGHT);
        }
    }

    public void onDrunkKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> GameController.getInstance().playTurn(MovementDir.M_DOWN);
            case DOWN -> GameController.getInstance().playTurn(MovementDir.M_UP);
            case LEFT -> GameController.getInstance().playTurn(MovementDir.M_RIGHT);
            case RIGHT -> GameController.getInstance().playTurn(MovementDir.M_LEFT);
        }
    }

    public void saveGameKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.S){
            try {
                DataSource dataSource = new DbManager().connect();
                SavedGameDao savedGameDao = new SavedGameDaoImpl(dataSource);
                SavedGameRepository savedGameRepository = new SavedGameRepositoryImpl(savedGameDao);
                Player player = GameController.getInstance().getPlayer();
                Actor[][] actorMatrix = GameController.getInstance().getActorController().getActorMatrix();
                Item[][] itemMatrix = GameController.getInstance().getItemController().getItemMatrix();
                Decor[][] decorMatrix = GameController.getInstance().getDecorController().getDecorMatrix();
                GameMap gameMap = GameController.getInstance().getMap();
                savedGameRepository.add(new SavedGame(player, actorMatrix, itemMatrix, decorMatrix, gameMap));
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    public void setDrunkKeyMapping(){
        scene.setOnKeyPressed(this::onDrunkKeyPressed);
    }

    public void setSoberKeyMapping(){
        scene.setOnKeyPressed(this::onSoberKeyPressed);
    }
}
