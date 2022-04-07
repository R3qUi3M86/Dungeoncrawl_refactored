package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.CombatSubcontroller;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.InteractionSubcontroller;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.MoveSubcontroller;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;

import java.util.ArrayList;

public class ActorController {
    private final MoveSubcontroller moveSubcontroller;
    private final CombatSubcontroller combatSubcontroller;
    private final InteractionSubcontroller interactionSubcontroller;

    private Actor[][] actorMatrix;
    private ArrayList<Actor> npcList = new ArrayList<>();
    private Player player;

    public ActorController(){
        moveSubcontroller = new MoveSubcontroller();
        combatSubcontroller = new CombatSubcontroller();
        interactionSubcontroller = new InteractionSubcontroller();
    }

    public void initNPCsMatrix(int x, int y){
        actorMatrix = new Actor[y][x];
    }

    public void setPlayer(Player player){
        this.player = player;
        actorMatrix[player.getX()][player.getY()] = player;
    }

    public void takePlayerTurn(MovementDir moveDir){
        Player player = GameController.getInstance().getPlayer();
        Cell targetCell = player.getCell().getNeighboringCell(moveDir.getDx(), moveDir.getDy());

        switch (targetCell.getType()){
            case INTERACTION -> interactionSubcontroller.playerInteract(targetCell, player); break;
            case ILLEGAL -> GameController.getInstance().travelToNextLevel(); break;
            case COLLISION -> moveSubcontroller.resolvePlayerCollision(); break;
            case WALKABLE -> playerFightOrMove(targetCell, moveDir);
        }
    }

    private void playerFightOrMove(Cell targetCell, MovementDir moveDir){
        Actor npc = actorMatrix[targetCell.getX()][targetCell.getY()];
        if (npc != null){
            combatSubcontroller.resolvePlayerCombat(npc);
        } else {
            moveSubcontroller.moveActor(player, moveDir);
        }
    }

    public void takeAllNPCTurn(){
        for(Actor npc : npcList){
            if (npc.isAlive())
                takeNPCTurn(npc);
        }
    }

    private void takeNPCTurn(Actor npc){
        MovementDir moveDir = npc.getPotentialMoveDirection();
        Cell targetCell = npc.getCell().getNeighboringCell(moveDir.getDx(), moveDir.getDy());
        npcFightOrMove(npc, targetCell, moveDir);
    }

    private void npcFightOrMove(Actor npc, Cell targetCell, MovementDir moveDir){
        if (targetCell.getX() == player.getX() && targetCell.getY() == player.getY()){
            combatSubcontroller.resolveMonsterCombat(npc);
        } else {
            moveSubcontroller.moveActor(npc, moveDir);
        }
    }

    public void addNpcToController(int x, int y, Actor actor){
        actorMatrix[y][x] = actor;
        npcList.add(actor);
    }

    public void removeNpcFromController(int x, int y, Actor actor){
        actorMatrix[y][x] = null;
        npcList.remove(actor);
    }

    public Actor[][] getActorMatrix() {
        return actorMatrix;
    }

    public void clearCorpses() {
        ArrayList<Actor> npcListCopy = new ArrayList<>(npcList);
        for (Actor actor : npcListCopy) {
            if (!actor.isAlive())
                removeNpcFromController(actor.getX(), actor.getY(), actor);
        }
    }
}
