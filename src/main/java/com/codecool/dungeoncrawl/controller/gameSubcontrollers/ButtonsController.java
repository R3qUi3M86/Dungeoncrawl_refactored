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
        setPickUpButton();
        setUseItButton();
        setEnterButton(player);
    }

    private void setPickUpButton() {
        Item item = GameController.getInstance().getItemController().getItemAtPlayerLocation();
        pickUpButton.setDisable(item == null || item.isConsumable());
    }

    private void setUseItButton() {
        Item item = GameController.getInstance().getItemController().getItemAtPlayerLocation();
        useItButton.setDisable(item == null || !item.isConsumable());
    }

    private void setEnterButton(Player player) {
        enterButton.setDisable(!player.getCell().isExit());
    }

    void pickUpEventHandler() {
        GameController.getInstance().getItemController().pickUpItem();
        GameController.getInstance().playTurn(MovementDir.M_NONE);
    }

    private void useItEventHandler() {
        GameController.getInstance().getItemController().useItem();
        GameController.getInstance().playTurn(MovementDir.M_NONE);
    }

    private void enterEventHandler(){
        GameController.getInstance().travelToNextLevel();
    }
}
