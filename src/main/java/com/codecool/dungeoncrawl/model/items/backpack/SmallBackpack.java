package com.codecool.dungeoncrawl.model.items.backpack;

import com.codecool.dungeoncrawl.model.Cell;

public class SmallBackpack extends Backpack {
    public SmallBackpack(Cell cell) {
        super(cell);
    }

    public SmallBackpack() {
        setCapacity();
    }

    @Override
    void setCapacity() {
        capacity = new int[]{2, 5};
        for (int i = 0; i < capacity[0]; i++){
            for (int j = 0; j < capacity[1]; j++){
                backpackItems.put(new BackpackCell(i, j), new EmptySpace());
            }
        }
    }

    @Override
    public String getTileName() {
        return "small backpack";
    }
}
