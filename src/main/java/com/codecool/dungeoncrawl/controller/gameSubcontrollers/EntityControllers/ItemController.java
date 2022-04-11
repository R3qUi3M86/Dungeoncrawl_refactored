package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.backpack.OperationResult;

public class ItemController {
    private Item[][] itemMatrix;
    private Player player;

    public void initItemMatrix(int x, int y){
        itemMatrix = new Item[x][y];
    }

    public void pickUpItem(){
        Player player = GameController.getInstance().getPlayer();
        Item item = itemMatrix[player.getX()][player.getY()];
        OperationResult pickUpResult = player.getBackpack().addItem(item);
        if (pickUpResult == OperationResult.SUCCESSFUL) {
            itemMatrix[player.getX()][player.getY()] = null;
        }
    }

    public void useItem(){
        Player player = GameController.getInstance().getPlayer();
        Item item = itemMatrix[player.getX()][player.getY()];
        item.useItem(player);
        itemMatrix[player.getX()][player.getY()] = null;
    }

    public void addItemToController(int x, int y, Item item){
        itemMatrix[x][y] = item;
    }

    public Item getItemAtPlayerLocation(){
        return itemMatrix[player.getX()][player.getY()];
    }

    public Item[][] getItemMatrix() {
        return itemMatrix;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
