package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class TreeTrunk extends Decor {
    public TreeTrunk(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return DecorType.TREE_TRUNK.toString();
    }

    @Override
    public DecorType getDecorType() {
        return DecorType.TREE_TRUNK;
    }
}
