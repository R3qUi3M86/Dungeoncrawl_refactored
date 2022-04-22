package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;

public class CombatController {
    public void resolveActorsCombat(Actor attacker, Actor defender) {
        if (attacker != defender) {
            defender.takeDamage(attacker.getAttack());
            if (defender.isAlive())
                attacker.takeDamage(defender.getAttack());
        }
    }

    public boolean checkPlayerCombat(Cell targetCell, Actor[][] actorMatrix, Player player) {
        Actor npc = actorMatrix[targetCell.getX()][targetCell.getY()];
        if (npc != null) {
            resolveActorsCombat(player, npc);
            return true;
        }
        return false;
    }
}
