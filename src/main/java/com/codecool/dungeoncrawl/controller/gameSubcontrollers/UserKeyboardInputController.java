package com.codecool.dungeoncrawl.controller.gameSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

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

    public void setDrunkKeyMapping(){
        scene.setOnKeyPressed(this::onDrunkKeyPressed);
    }

    public void setSoberKeyMapping(){
        scene.setOnKeyPressed(this::onSoberKeyPressed);
    }
}
