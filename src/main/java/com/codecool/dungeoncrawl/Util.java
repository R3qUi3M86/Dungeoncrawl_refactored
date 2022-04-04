package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
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
        Actor actor = cell.getActor();
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 & j != 0) {
                    if (!actor.isActorOutOfMap(i, j))
                        adjacentCells.add(cell.getNeighboringCell(i, j));
                }
            }
        }
        return adjacentCells;
    }

    public static ArrayList<Cell> getValidAdjacentCells(Cell cell) {
        ArrayList<Cell> adjacentCells = getAdjacentCells(cell);
        ArrayList<Cell> adjacentCellsCopy = new ArrayList<>(adjacentCells);
        for (Cell adjacentCell : adjacentCellsCopy) {
            if (adjacentCell.getType() != CellType.FLOOR || adjacentCell.getActor() != null) {
                adjacentCells.remove(adjacentCell);
            }
        }
        return adjacentCells;
    }
}
