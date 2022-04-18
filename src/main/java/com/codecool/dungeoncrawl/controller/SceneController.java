package com.codecool.dungeoncrawl.controller;

public class SceneController {
    GameController.getInstance().startGame();
    GameController.getInstance().initGame();
    ViewController.getInstance().initWindow(primaryStage);
}
