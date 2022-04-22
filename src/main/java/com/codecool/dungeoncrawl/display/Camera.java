package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.map.GameMap;

public class Camera {
    private final int hRange;
    private final int vRange;
    private final GameMap map;
    private int[] targetField;
    private final CameraField[][] matrixInView;
    private final int hMinDistance;
    private final int vMinDistance;

    public Camera(GameMap map, int[] targetField, int hRange, int vRange) {
        this.hRange = hRange;
        this.vRange = vRange;
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
        for (int x = 0; x < hRange; x++) {
            for (int y = 0; y < vRange; y++) {
                int mapX = targetField[0] - hMinDistance + x;
                int mapY = targetField[1] - vMinDistance + y;
                matrixInView[x][y] = new CameraField(mapX, mapY);
            }
        }
    }

    public void followPlayer(Player player) {
        targetField = new int[]{player.getX(), player.getY()};
        setValidTargetField();
        setMatrixInView();
    }

    public void moveAsRequired(Player player) {
        int playerInCameraX = targetField[0] - player.getX() + hMinDistance;
        int playerInCameraY = targetField[1] - player.getY() + vMinDistance;
        int cameraFollowDistanceX = hMinDistance / 2;
        int cameraFollowDistanceY = vMinDistance / 2;

        if (playerInCameraX < cameraFollowDistanceX) {
            targetField[0] = targetField[0] + 1;
        } else if (hRange - playerInCameraX < cameraFollowDistanceX) {
            targetField[0] = targetField[0] - 1;
        }
        if (playerInCameraY < cameraFollowDistanceY) {
            targetField[1] = targetField[1] + 1;
        } else if (vRange - playerInCameraY < cameraFollowDistanceY) {
            targetField[1] = targetField[1] - 1;
        }
        if (player.isWasted()) {
            shakeCamera(targetField);
        }
        setValidTargetField();
        setMatrixInView();
    }

    private void shakeCamera(int[] targetField) {
        int rndResult = (int) (Math.random() * 9);
        switch (rndResult) {
            case 1 -> targetField[0] = targetField[0] + 1;
            case 2 -> targetField[0] = targetField[0] - 1;
            case 3 -> targetField[1] = targetField[1] + 1;
            case 4 -> targetField[1] = targetField[1] - 1;
            case 5 -> {
                targetField[0] = targetField[0] + 1;
                targetField[1] = targetField[1] + 1;
            }
            case 6 -> {
                targetField[0] = targetField[0] - 1;
                targetField[1] = targetField[1] + 1;
            }
            case 7 -> {
                targetField[0] = targetField[0] + 1;
                targetField[1] = targetField[1] - 1;
            }
            case 8 -> {
                targetField[0] = targetField[0] - 1;
                targetField[1] = targetField[1] - 1;
            }
        }
    }

    public CameraField[][] getMatrixInView() {
        return matrixInView;
    }
}
