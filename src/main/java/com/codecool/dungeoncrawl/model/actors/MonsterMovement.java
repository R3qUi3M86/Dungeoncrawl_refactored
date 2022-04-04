package com.codecool.dungeoncrawl.model.actors;

public enum MonsterMovement {
    M_UP(0, -1), M_RIGHT(1, 0), M_DOWN(0, 1), M_LEFT(-1, 0);

    private final int dx;
    private final int dy;

    MonsterMovement(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
