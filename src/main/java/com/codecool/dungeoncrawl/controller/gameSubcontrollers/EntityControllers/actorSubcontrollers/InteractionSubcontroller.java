package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.EmptySpace;
import com.codecool.dungeoncrawl.model.map.GameMap;

import java.util.Objects;

public class InteractionSubcontroller {
    public void playerInteract(Cell cell, Player player){

    }

    public void move(int dx, int dy) {
        if (!isActorOutOfMap(dx, dy)){
            Cell nextCell = cell.getNeighboringCell(dx, dy);
            if (nextCell.getType() == CellType.COLLISION) {
                return;
            } else if (nextCell.getActor() != null) {
                combat(nextCell);
                return;
            } else if (nextCell.getType() == CellType.INTERACTION) {
                if (this.getTileName().equals("player")) { // override in player
                    Player player = (Player) this;
                    Backpack backpack = player.getBackpack();
                    for (BackpackCell backpackCell : backpack.getBackpackItems().keySet()) {
                        Item item = backpack.getBackpackItems().get(backpackCell);
                        if (Objects.equals(item.getCellImageName(), "key")) {
                            nextCell.setType(CellType.OPENED_DOOR);
                            backpack.getBackpackItems().put(backpackCell, new EmptySpace());
                            return;
                        }
                    }
                }
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
