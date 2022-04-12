package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.map.GameMap;

import java.util.ArrayList;

public class Camera {
    private final GameMap map;
    private int[] targetField;
    private final CameraField[][] matrixInView;
    private final int hMinDistance;
    private final int vMinDistance;


    public Camera(GameMap map, int[] targetField, int hRange, int vRange) {
        this.map = map;
        this.targetField = targetField; // od 1
        matrixInView = new CameraField[hRange][vRange];
        this.hMinDistance = (hRange - 1) / 2;
        this.vMinDistance = (vRange - 1) / 2;
        setValidTargetField();
        setMatrixInView();
    }

    private void setValidTargetField() {
        int hDistToMapBorder = map.getWidth() - 1 - targetField[0];
        int vDistToMapBorder = map.getHeight() - 1 - targetField[1];

        if (targetField[0] < hMinDistance) {
            targetField[0] = hMinDistance;
        } else if (hDistToMapBorder < hMinDistance) {
            targetField[0] = map.getWidth() - 1 - hMinDistance;
        }
        if (targetField[1] < vMinDistance) {
            targetField[1] = vMinDistance;
        } else if (vDistToMapBorder < vMinDistance) {
            targetField[1] = map.getHeight() - 1 - vMinDistance;
        }
    }

    private void setMatrixInView() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int mapX = targetField[0] - hMinDistance + x;
                int mapY = targetField[1] - vMinDistance + y;

                matrixInView[x][y] = new CameraField(mapX, mapY);
            }
        }
    }

    public void followPlayer(Player player){
        targetField = new int[]{player.getX(), player.getY()};
        setValidTargetField();
        setMatrixInView();
    }

    public CameraField[][] getMatrixInView() {
        return matrixInView;
    }
}
