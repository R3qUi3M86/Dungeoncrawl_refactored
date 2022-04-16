package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ActorTest {
    @Test
    void playerTurnTest(){
        //given

        //when

        //then
    }





















//    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

//    @Test
//    void moveUpdatesCells() {
//        Player player = new Player();
//        player.move(1, 0);
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//        assertNull(gameMap.getCell(1, 1).getActor());
//        assertEquals(player, gameMap.getCell(2, 1).getActor());
//    }
//
//    @Test
//    void cannotMoveIntoWall() {
//        gameMap.getCell(2, 1).setType(CellType.WALL);
//        Player player = new Player();
//        player.move(1, 0);
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//    }
//    @Disabled
//    @Test
//    void cannotMoveOutOfMap() {
//        Player player = new Player();
//        player.move(1, 0);
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//    }
//    @Disabled
//    @Test
//    void cannotMoveIntoAnotherActor() {
//        Player player = new Player();
//        Rat rat = new Rat(gameMap.getCell(2, 1));
//        player.move(1, 0);
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//        assertEquals(2, rat.getX());
//        assertEquals(1, rat.getY());
//        assertEquals(rat, gameMap.getCell(2, 1).getActor());
//    }
}