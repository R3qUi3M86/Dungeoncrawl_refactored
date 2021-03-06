package com.codecool.dungeoncrawl.model.items.backpack;

import java.io.Serializable;
import java.util.Objects;

public class BackpackCell implements Serializable {
    private final int x;
    private final int y;

    public BackpackCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BackpackCell that = (BackpackCell) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
