package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameController.getInstance().initGame();
        ViewController.getInstance().initWindow(primaryStage);
        GameController.getInstance().startGame();
    }
}
