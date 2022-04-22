package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.database.*;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javax.sql.DataSource;
import java.sql.SQLException;

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
//        GameController.getInstance().initGame(savedGame.getPlayer());
        try {
            DataSource dataSource = new DbManager().connect();
            SavedGameDao savedGameDao = new SavedGameDaoImpl(dataSource);
            SavedGameRepository savedGameRepository = new SavedGameRepositoryImpl(savedGameDao);
            SavedGame savedGame = savedGameRepository.get(2);
            System.out.println(savedGame); // TODO
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void quitGame() {
        System.exit(0);
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

}
