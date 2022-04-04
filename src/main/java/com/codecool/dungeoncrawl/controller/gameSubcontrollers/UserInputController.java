package com.codecool.dungeoncrawl.controller.gameSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.model.actors.Player;
import javafx.scene.input.KeyEvent;

public class UserInputController {

    public void onKeyPressed(KeyEvent keyEvent) {
        Player player = GameController.getInstance().getPlayer();
        switch (keyEvent.getCode()) {
            case UP:
                player.move(0, -1);
                GameController.getInstance().playTurn();
                break;
            case DOWN:
                player.move(0, 1);
                GameController.getInstance().playTurn();
                break;
            case LEFT:
                player.move(-1, 0);
                GameController.getInstance().playTurn();
                break;
            case RIGHT:
                player.move(1, 0);
                GameController.getInstance().playTurn();
                break;
        }
    }

    public void onDrunkKeyPressed(KeyEvent keyEvent) {
        Player player = GameController.getInstance().getPlayer();
        switch (keyEvent.getCode()) {
            case UP:
                player.move(0, 1);
                GameController.getInstance().playTurn();
                break;
            case DOWN:
                player.move(0, -1);
                GameController.getInstance().playTurn();
                break;
            case LEFT:
                player.move(1, 0);
                GameController.getInstance().playTurn();
                break;
            case RIGHT:
                player.move(-1, 0);
                GameController.getInstance().playTurn();
                break;
        }
    }
}
