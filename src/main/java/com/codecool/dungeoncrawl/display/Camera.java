package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.model.map.GameMap;

import java.util.ArrayList;

public class Camera {
    private final int hRange;
    private final int vRange;
    private int xPos;
    private int yPos;
    private GameMap map;
    private int[] targetField;
    private CameraField[][] matrixInView;

    public Camera(GameMap map, int[] targetField, int hRange, int vRange){
        this.map = map;
        this.targetField = targetField; // od 1
        this.hRange = hRange;
        this.vRange = vRange;
        setValidTargetField();
        initMatrixInView();
    }

    private void setValidTargetField(){
        int hMinDistance = (hRange - 1) / 2;
        int vMinDistance = (vRange - 1) / 2;
        int hDistToMapBorder = map.getWidth() - 1 - targetField[0];
        int vDistToMapBorder = map.getHeight() - 1 - targetField[1];

        if (targetField[0] < hMinDistance){
            targetField[0] = hMinDistance;
        } else if (hDistToMapBorder < hMinDistance){
            targetField[0] = map.getWidth() - 1 - hMinDistance;
        }
        if (targetField[1] < vMinDistance){
            targetField[1] = vMinDistance;
        } else if (vDistToMapBorder < vMinDistance){
            targetField[1] = map.getHeight() - 1 - vMinDistance;
        }
    }

    private void initMatrixInView(){
        matrixInView = new CameraField[hRange][vRange];

    }
}
