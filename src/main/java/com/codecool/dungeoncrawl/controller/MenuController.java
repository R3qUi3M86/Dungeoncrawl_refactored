package com.codecool.dungeoncrawl.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button loadGameButton;
    @FXML
    private Button quitGameButton;
    @FXML
    private Button newGameButton;

    private Stage primaryStage;

    public void initialize(){
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
        //TODO
    }

    public void quitGame() {
        System.exit(0);
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

}
