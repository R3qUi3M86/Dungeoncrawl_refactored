package com.codecool.dungeoncrawl.model.items.backpack;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Backpack extends Item {
    int[] capacity;
    Map<BackpackCell, Item> backpackItems = new HashMap<>();

    public Backpack(Cell cell) {
        super(cell);
    }

    public Backpack() {}

    abstract void setCapacity();

    public int[] getCapacity() {
        return capacity;
    }

    public OperationResult addItem(Item item){
        for (int i = 0; i < capacity[0]; i++){
            for (int j = 0; j < capacity[1]; j++){
                BackpackCell backpackCell = new BackpackCell(i, j);
                if (Objects.equals(backpackItems.get(backpackCell).getCellImageName(), "empty")) {
                    backpackItems.put(backpackCell, item);
                    return OperationResult.SUCCESSFUL;
                }
            }
        }
        return OperationResult.FAILED;
    }

    public Map<BackpackCell, Item> getBackpackItems() {
        return backpackItems;
    }
}
