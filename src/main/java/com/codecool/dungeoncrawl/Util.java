package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.map.GameMap;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.util.ArrayList;

public class Util {
    public static Node getByUserData(Parent parent, Object data) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node.getUserData() != null && node.getUserData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    public static ArrayList<Cell> getAdjacentCells(Cell cell) {
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 & j != 0) {
                    if (!cellIsOutOfMap(cell.getX() + i, cell.getY() + j))
                        adjacentCells.add(cell.getNeighboringCell(i, j));
                }
            }
        }
        return adjacentCells;
    }

    public static ArrayList<Cell> getValidAdjacentCells(Cell cell) {
        ArrayList<Cell> adjacentCells = getAdjacentCells(cell);
        ArrayList<Cell> adjacentCellsCopy = new ArrayList<>(adjacentCells);
        Actor[][] actorMatrix = GameController.getInstance().getActorController().getActorMatrix();
        for (Cell adjacentCell : adjacentCellsCopy) {
            if (adjacentCell.getType() != CellType.WALKABLE || actorMatrix[adjacentCell.getX()][adjacentCell.getY()] != null) {
                adjacentCells.remove(adjacentCell);
            }
        }
        return adjacentCells;
    }

    public static boolean cellIsOutOfMap(int x, int y){
        GameMap map = GameController.getInstance().getMap();
        int mapWidth = map.getWidth();
        int mapHeight = map.getHeight();
        if ((x < 0) || (x >= mapWidth))
            return true;
        else return (y < 0) || (y >= mapHeight);
    }
}
