package com.codecool.dungeoncrawl.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class MenuController {
    public Button loadGameButton;
    public Button quitGameButton;
    public Button newGameButton;
    public Stage primaryStage;
    public Scene menuScene;


    public MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        newGameButton.setOnAction(e -> newGame());
        loadGameButton.setOnAction(e -> loadGame());
        quitGameButton.setOnAction(e -> quitGame());
    }

    public void newGame() {
        GameController.getInstance().initGame();
        ViewController.getInstance().initWindow(primaryStage);
        GameController.getInstance().startGame();
    }

    public void loadGame() {
    }

    public void quitGame() {
    }
}
