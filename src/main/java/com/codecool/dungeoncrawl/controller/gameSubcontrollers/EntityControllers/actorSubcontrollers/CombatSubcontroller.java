package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers;

import com.codecool.dungeoncrawl.model.actors.Actor;

public class CombatSubcontroller {
    public void resolveActorsCombat(Actor attacker, Actor defender) {
        if (attacker != defender) {
            defender.takeDamage(attacker.getAttack());
            if (defender.isAlive())
                attacker.takeDamage(defender.getAttack());
        }
    }
}
