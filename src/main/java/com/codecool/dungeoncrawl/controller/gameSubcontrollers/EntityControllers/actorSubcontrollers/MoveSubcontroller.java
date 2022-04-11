package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;

import java.util.List;

public class MoveSubcontroller {

    public void moveActor(Actor actor, MovementDir movementDir){
        Actor[][] actorMatrix = GameController.getInstance().getActorController().getActorMatrix();
        actorMatrix[actor.getX()][actor.getY()] = null;
        actor.setCell(actor.getCell().getNeighboringCell(movementDir.getDx(), movementDir.getDy()));
        actorMatrix[actor.getX()][actor.getY()] = actor;
    }

    public MovementDir moveInRandomDirection(List<MovementDir> moves){
        return moves.get((int) (Math.random() * 4));
    }

    public MovementDir moveInSequence(List<MovementDir> moves, MovementDir lastMove){
        int i = 0;
        MovementDir newMove = lastMove;
        for (MovementDir moveDir : moves) {
            if (lastMove == moveDir) {
                if (i + 1 == moves.size()) {
                    newMove = moves.get(0);
                } else {
                    newMove = moves.get(i + 1);
                }
                break;
            }
            i++;
        }
        return newMove;
    }

    public boolean moveToWalkableCell(Cell targetCell){
        return targetCell.getType() == CellType.WALKABLE;
    }

    public boolean moveToVacantCell(Cell targetCell){
        Actor[][] actorMatrix = GameController.getInstance().getActorController().getActorMatrix();
        return actorMatrix[targetCell.getX()][targetCell.getY()] == null;
    }

    public MovementDir applyMovementModifiers(Player player, MovementDir movementDir){
        if(player.isSlowed()){
            player.setSlowed(false);
            return MovementDir.M_NONE;
        } else {
            return movementDir;
        }
    }
}
