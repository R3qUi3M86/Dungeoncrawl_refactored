package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneController sceneController = new SceneController(primaryStage);
        sceneController.showMainMenu();
    }
}
