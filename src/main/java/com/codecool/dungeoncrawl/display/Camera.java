package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.model.map.GameMap;

import java.util.ArrayList;

public class Camera {
    private final int hRange;
    private final int vRange;
    private int xPos;
    private int yPox;
    private GameMap map;
    private int[] targetField;
    private CameraField[][] matrixInView;

    public Camera(GameMap map, int[] targetField, int hRange, int vRange){
        this.map = map;
        this.targetField = targetField;
        this.hRange = hRange;
        this.vRange = vRange;
        setValidTargetField();
        initMatrixInView();
    }

    private void setValidTargetField(){

    }

    private void initMatrixInView(){
        matrixInView = new CameraField[hRange][vRange];

    }
}
