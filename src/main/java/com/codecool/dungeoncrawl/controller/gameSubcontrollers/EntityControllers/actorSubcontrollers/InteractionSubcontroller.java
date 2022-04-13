package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellImage;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.Key;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.EmptySpace;

import java.util.Arrays;

public class InteractionSubcontroller {
    public void playerInteract(Cell cell, Player player, MovementDir movementDir){
        CellImage cellImage = cell.getImageType();
        switch (cellImage){
            case CLOSED_DOOR -> tryToOpenDoor(cell, player);
            case DECORATION -> GameController.getInstance().getDecorController().tryToInteractWithDecor(cell, movementDir);
        }
    }

    private void tryToOpenDoor(Cell cell, Player player) {
        Backpack backpack = player.getBackpack();
        String[] keyNames = {"golden key","red key","blue key"};
        for (BackpackCell backpackCell : backpack.getBackpackItems().keySet()) {
            Item item = backpack.getBackpackItems().get(backpackCell);
            if (Arrays.asList(keyNames).contains(item.getCellImageName()) && cell.getDoor().keyMatches((Key) item)) {
                cell.setType(CellType.WALKABLE);
                cell.setImageType(CellImage.OPENED_DOOR);
                cell.getDoor().setOpen(true);
                backpack.getBackpackItems().put(backpackCell, new EmptySpace());
                return;
            }
        }
    }

    public void interactWithSpiderWeb(MovementDir movementDir, Player player){
        GameController.getInstance().getActorController().getMoveSubcontroller().moveActor(player, movementDir);
        if (movementDir != MovementDir.M_NONE)
            player.setSlowed(true);
    }

    public void interactWithShrine(Decor shrine, Player player){
        if (!shrine.isUsed()){
            player.setMaxHealth(player.getMaxHealth() + 10);
            player.eatFood(99999);
        }
        shrine.setUsed(true);
    }
}
