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
        int hDistToMapBorder = map.getWidth() - targetField[0];
        int vDistToMapBorder = map.getHeight() - targetField[1];

        if (targetField[0] <= hMinDistance){
            targetField[0] = hMinDistance + 1;
        } else if (hDistToMapBorder <= hMinDistance){
            targetField[0] = map.getWidth() - hMinDistance - 1;
        }
        if (targetField[1] <= vMinDistance){
            targetField[1] = vMinDistance + 1;
        } else if (vDistToMapBorder <= vMinDistance){
            targetField[1] = map.getHeight() - vMinDistance - 1;
        }
    }

    private void initMatrixInView(){
        matrixInView = new CameraField[hRange][vRange];

    }
}
