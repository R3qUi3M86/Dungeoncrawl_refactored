package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellImage;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.EmptySpace;

import java.util.Objects;

public class InteractionSubcontroller {
    public void playerInteract(Cell cell, Player player){
        CellImage cellImage = cell.getImageType();
        switch (cellImage){
            case CLOSED_DOOR -> tryToOpenDoor(cell, player);
        }
    }

    private void tryToOpenDoor(Cell cell, Player player) {
        Backpack backpack = player.getBackpack();
        for (BackpackCell backpackCell : backpack.getBackpackItems().keySet()) {
            Item item = backpack.getBackpackItems().get(backpackCell);
            if (Objects.equals(item.getCellImageName(), "key")) {
                cell.setType(CellType.WALKABLE);
                cell.setImageType(CellImage.OPENED_DOOR);
                backpack.getBackpackItems().put(backpackCell, new EmptySpace());
                return;
            }
        }
    }
}
