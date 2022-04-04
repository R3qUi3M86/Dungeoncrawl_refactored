package com.codecool.dungeoncrawl.controller.gameSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.items.Item;
import javafx.scene.control.Button;

public class ButtonsController {
    Button pickUpButton;
    Button useItButton;
    Button enterButton;

    public ButtonsController(Button pickUpButton, Button useItButton, Button enterButton) {
        this.pickUpButton = pickUpButton;
        this.useItButton = useItButton;
        this.enterButton = enterButton;
    }

    public void setGUIButtonsEventHandlers() {
        pickUpButton.setOnAction(e -> pickUpEventHandler());
        useItButton.setOnAction(e -> useItEventHandler());
        enterButton.setOnAction(e -> enterEventHandler());
    }

    public void setPlayerGUIButtons(Player player){
        setPickUpButton(player.getCell());
        setUseItButton(player.getCell());
        setEnterButton(player.getCell());
    }

    private void setPickUpButton(Cell playerCell) {
        Item item = playerCell.getItem();
        pickUpButton.setDisable(item == null || item.isConsumable());
    }

    private void setUseItButton(Cell playerCell) {
        Item item = playerCell.getItem();
        useItButton.setDisable(item == null || !item.isConsumable());
    }

    private void setEnterButton(Cell playerCell) {
        enterButton.setDisable(!playerCell.isExit());
    }

    void pickUpEventHandler() {
        Player player = GameController.getInstance().getPlayer();
        Item item = player.getCell().getItem();
        player.getBackpack().addItem(item);
        player.getCell().removeItem();
        GameController.getInstance().playTurn();
    }

    private void useItEventHandler() {
        Player player = GameController.getInstance().getPlayer();
        Item item = player.getCell().getItem();
        item.useItem(player);
        player.getCell().removeItem();
    }

    private void enterEventHandler(){
        enterButton.setDisable(true);
        GameController.getInstance().setNextMap();
        GameController.getInstance().travelToNextLevel();
    }
}
