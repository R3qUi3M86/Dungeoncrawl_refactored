package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.items.Item;

public class ItemController {
    private Item[][] itemMatrix;
    private Player player;

    public void initItemMatrix(int x, int y){
        itemMatrix = new Item[y][x];
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void addItemToController(int x, int y, Item item){
        itemMatrix[y][x] = item;
    }

    public Item[][] getItemMatrix() {
        return itemMatrix;
    }
}
