package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class SceneController {
    public Scene menuScene;
    private final Stage primaryStage;



    public SceneController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }


    public void showMainMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fxml/mainMenu.fxml"));
        loadScene(fxmlLoader);
        primaryStage.setScene(menuScene);
    }


    private void loadScene(FXMLLoader fxmlLoader) {
        try {
            this.menuScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Could not load resource!");
        }
    }

}
