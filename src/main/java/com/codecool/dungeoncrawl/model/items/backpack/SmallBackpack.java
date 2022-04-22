package com.codecool.dungeoncrawl.model.items.backpack;

public class SmallBackpack extends Backpack {

    public SmallBackpack() {
        setCapacity();
    }

    @Override
    void setCapacity() {
        capacity = new int[]{2, 5};
        for (int i = 0; i < capacity[0]; i++) {
            for (int j = 0; j < capacity[1]; j++) {
                backpackItems.put(new BackpackCell(i, j), new EmptySpace());
            }
        }
    }

    @Override
    public String getCellImageName() {
        return "small backpack";
    }
}
