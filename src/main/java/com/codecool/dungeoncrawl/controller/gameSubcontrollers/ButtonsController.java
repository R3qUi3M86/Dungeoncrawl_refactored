package com.codecool.dungeoncrawl.controller.gameSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
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
        setPickUpButton(player);
        setUseItButton(player);
        setEnterButton(player);
    }

    private void setPickUpButton(Player player) {
        Item[][] itemMatrix = GameController.getInstance().getItemController().getItemMatrix();
        Item item = itemMatrix[player.getX()][player.getY()];
        pickUpButton.setDisable(item == null || item.isConsumable());
    }

    private void setUseItButton(Player player) {
        Item[][] itemMatrix = GameController.getInstance().getItemController().getItemMatrix();
        Item item = itemMatrix[player.getX()][player.getY()];
        useItButton.setDisable(item == null || !item.isConsumable());
    }

    private void setEnterButton(Player player) {
        enterButton.setDisable(!player.getCell().isExit());
    }

    void pickUpEventHandler() {
        Player player = GameController.getInstance().getPlayer();
        Item[][] itemMatrix = GameController.getInstance().getItemController().getItemMatrix();
        Item item = itemMatrix[player.getX()][player.getY()];
        player.getBackpack().addItem(item);
        itemMatrix[player.getX()][player.getY()] = null;
        GameController.getInstance().playTurn(MovementDir.M_NONE);
    }

    private void useItEventHandler() {
        Player player = GameController.getInstance().getPlayer();
        Item[][] itemMatrix = GameController.getInstance().getItemController().getItemMatrix();
        Item item = itemMatrix[player.getX()][player.getY()];
        item.useItem(player);
        itemMatrix[player.getX()][player.getY()] = null;
        GameController.getInstance().playTurn(MovementDir.M_NONE);
    }

    private void enterEventHandler(){
        GameController.getInstance().travelToNextLevel();
    }
}
