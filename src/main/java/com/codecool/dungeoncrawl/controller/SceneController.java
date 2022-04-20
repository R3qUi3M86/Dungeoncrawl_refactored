package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneController {
    public Scene menuScene;
    private final Stage primaryStage;



    public SceneController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }


    public void showMainMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL location = Main.class.getResource("/mainMenu.fxml");
        System.out.println(location);
        fxmlLoader.setLocation(Main.class.getResource("/mainMenu.fxml"));
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
