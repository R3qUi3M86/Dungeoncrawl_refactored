package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.CombatSubcontroller;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.InteractionSubcontroller;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.MoveSubcontroller;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.*;

import java.util.ArrayList;

public class ActorController {
    private final MoveSubcontroller moveSubcontroller;
    private final CombatSubcontroller combatSubcontroller;
    private final InteractionSubcontroller interactionSubcontroller;

    private Actor[][] actorMatrix;
    private ArrayList<Actor> npcList;
    private Player player;

    public ActorController() {
        moveSubcontroller = new MoveSubcontroller();
        combatSubcontroller = new CombatSubcontroller();
        interactionSubcontroller = new InteractionSubcontroller();
    }

    public void initNPCsMatrix(int x, int y) {
        npcList = new ArrayList<>();
        actorMatrix = new Actor[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
        actorMatrix[player.getX()][player.getY()] = player;
    }

    public void takePlayerTurn(MovementDir moveDir) {
        Player player = GameController.getInstance().getPlayer();
        moveDir = moveSubcontroller.applyMovementModifiers(player, moveDir);
        Cell targetCell = player.getCell().getNeighboringCell(moveDir.getDx(), moveDir.getDy());
        if (combatSubcontroller.checkPlayerCombat(targetCell, actorMatrix, player))
            return;
        if (!Util.cellIsOutOfMap(player.getX() + moveDir.getDx(), player.getY() + moveDir.getDy())) {
            switch (targetCell.getType()) {
                case INTERACTION -> interactionSubcontroller.playerInteract(targetCell, player, moveDir);
                case WALKABLE -> moveSubcontroller.moveActor(player, moveDir);
            }
        }
    }

    public void takeAllNPCTurn() {
        for (Actor npc : npcList) {
            if (npc.isAlive())
                takeNPCTurn(npc);
        }
    }

    private void takeNPCTurn(Actor npc) {
        Mob mob = (Mob) npc;
        MovementDir moveDir = mob.getPotentialMoveDirection();
        if (!Util.cellIsOutOfMap(npc.getX() + moveDir.getDx(), npc.getY() + moveDir.getDy())) {
            Cell targetCell = npc.getCell().getNeighboringCell(moveDir.getDx(), moveDir.getDy());
            npcFightOrMove(npc, targetCell, moveDir);
        }
    }

    private void npcFightOrMove(Actor npc, Cell targetCell, MovementDir moveDir) {
        if (targetCell.getX() == player.getX() && targetCell.getY() == player.getY()) {
            combatSubcontroller.resolveActorsCombat(npc, player);
        } else if (moveSubcontroller.moveToWalkableCell(targetCell) && moveSubcontroller.moveToVacantCell(targetCell)) {
            moveSubcontroller.moveActor(npc, moveDir);
        }
    }

    public Actor[][] getActorMatrix() {
        return actorMatrix;
    }

    public void clearCorpses() {
        clearNpcCorpses();
        clearPlayerCorpse();
    }

    private void clearNpcCorpses() {
        ArrayList<Actor> npcListCopy = new ArrayList<>(npcList);
        for (Actor actor : npcListCopy) {
            if (!actor.isAlive())
                removeNpcFromController(actor.getX(), actor.getY(), actor);
        }
    }

    private void clearPlayerCorpse() {
        if (!player.isAlive()) {
            actorMatrix[player.getX()][player.getY()] = null;
        }
    }

    public void addNpcToController(int x, int y, Actor actor) {
        actorMatrix[x][y] = actor;
        npcList.add(actor);
    }

    public void removeNpcFromController(int x, int y, Actor actor) {
        actorMatrix[x][y] = null;
        npcList.remove(actor);
        if (actor.isMinion()) {
            removeMinion((Minion) actor);
        }
    }

    private void removeMinion(Minion actor) {
        actor.getMaster().getMinions().remove(actor);
    }

    public void resolveActorTimedEffects() {
        player.resolveEffects();
        ArrayList<Actor> npcListCopy = new ArrayList<>(npcList);
        for (Actor npc : npcListCopy) {
            npc.resolveEffects();
        }
    }

    public MoveSubcontroller getMoveSubcontroller() {
        return moveSubcontroller;
    }

    public InteractionSubcontroller getInteractionSubcontroller() {
        return interactionSubcontroller;
    }
}
