package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    public Scene menuScene;
    private final Stage primaryStage;
    private MenuController menuController;

    public SceneController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void showMainMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
        loadScene(fxmlLoader);
        menuController = fxmlLoader.getController();
        menuController.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.setResizable(false);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }


    private void loadScene(FXMLLoader fxmlLoader) {
        try {
            this.menuScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Could not load resource!");
            e.printStackTrace();
        }
    }

    public Scene getMenuScene() {
        return menuScene;
    }
}
