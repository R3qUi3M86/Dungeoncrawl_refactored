package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class CombatSubcontroller {
    private void combat(Cell nextCell) {
        if (!this.getTileName().equals("player") && !nextCell.getActor().getTileName().equals("player"))
            return;
        nextCell.getActor().takeDamage(getAttack());
        if (nextCell.getActor().isAlive()){
            takeDamage(nextCell.getActor().getAttack());
            if (!alive){
                cell.setActor(null);
            }
        } else {
            nextCell.setActor(null);
        }
    }
}
