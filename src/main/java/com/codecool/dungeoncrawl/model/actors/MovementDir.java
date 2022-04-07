package com.codecool.dungeoncrawl.model.actors;

public enum MovementDir {
    M_UP(0, -1), M_RIGHT(1, 0), M_DOWN(0, 1), M_LEFT(-1, 0), M_NONE(0,0);

    private final int dx;
    private final int dy;

    MovementDir(int dx, int dy) {
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
