package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.EmptySpace;

import java.util.Objects;

public class MoveSubcontroller {

    public void moveActor(Actor actor, MovementDir movementDir){
        Actor[][] actorMatrix = GameController.getInstance().getActorController().getActorMatrix();
        actorMatrix[actor.getX()][actor.getY()] = null;
        actor.setCell(actor.getCell().getNeighboringCell(movementDir.getDx(), movementDir.getDy()));
        actorMatrix[actor.getX()][actor.getY()] = actor;
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

    public boolean isActorOutOfMap(int dx, int dy){
        int mapWidth = cell.getGameMap().getWidth();
        int mapHeight = cell.getGameMap().getHeight();
        if ((dx == 1) && (this.getX() + dx == mapWidth))
            return true;
        else if ((dx == -1) && (this.getX() + dx == -1))
            return true;
        else if ((dy == 1) && (this.getY() + dy == mapHeight))
            return true;
        else return (dx == -1) && (this.getY() + dy == -1);
    }
}
