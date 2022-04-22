package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.controller.ViewController;
import com.codecool.dungeoncrawl.display.tiles.Tile;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Map;

public class PlayerGUI {


    private GridPane ui;
    private final Label healthPts = new Label();

    private Button pickUpButton;
    private Button useItButton;
    private Button enterButton;

    public void initPlayerGUI() {
        this.ui = new GridPane();
        pickUpButton = new Button("Pick up");
        useItButton = new Button("Use");
        enterButton = new Button("Enter");
        pickUpButton.setDisable(true);
        useItButton.setDisable(true);
        enterButton.setDisable(true);

        setGUIGridConstraints();
        setGUIElements();
    }

    private void setGUIGridConstraints() {
        ui.setGridLinesVisible(false);
        ui.setHgap(10);
        ui.setPrefHeight(640);
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.setBackground(new Background(new BackgroundFill(Color.rgb(207, 198, 184), CornerRadii.EMPTY, Insets.EMPTY)));

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints(100, 100, Double.MAX_VALUE);
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints(200);

        row2.setVgrow(Priority.ALWAYS);
        ui.getRowConstraints().addAll(row1, row2, row3, row4);
    }

    private void setGUIElements() {
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthPts, 1, 0);
        ui.add(new Label("Backpack: "), 0, 2);

        ui.add(pickUpButton, 0, 4, 1, 1);
        ui.add(enterButton, 3, 4, 1, 1);
        ui.add(useItButton, 1, 4, 2, 1);
    }

    public void drawPlayerGui(Player player) {
        updateHealth(player.getHealth());
        drawBackpack(player.getBackpack());
    }

    private void drawBackpack(Backpack playerBackpack) {
        Node backpackNode = Util.getByUserData(ui, "backpack");
        ui.getChildren().remove(backpackNode);
        GridPane backpackCells = new GridPane();
        backpackCells.setUserData("backpack");
        Map<BackpackCell, Item> items = playerBackpack.getBackpackItems();
        int[] capacity = playerBackpack.getCapacity();
        for (int i = 0; i < capacity[0]; i++) {
            for (int j = 0; j < capacity[1]; j++) {
                Canvas backpackCellCanvas = new Canvas(Tile.TILE_WIDTH, Tile.TILE_WIDTH);
                GraphicsContext cellContext = backpackCellCanvas.getGraphicsContext2D();
                cellContext.setFill(Color.BLACK);
                cellContext.fillRect(0, 0, backpackCellCanvas.getWidth(), backpackCellCanvas.getHeight());
                Item itemInBackpack = items.get(new BackpackCell(i, j));
                ViewController.getInstance().drawTile(cellContext, itemInBackpack, 0, 0);
                backpackCells.add(backpackCellCanvas, j, i);
            }
        }
        ui.add(backpackCells, 0, 3, 4, 1);
    }

    public void updateHealth(int health) {
        healthPts.setText("" + health);
    }

    public GridPane getUi() {
        return ui;
    }

    public Button getPickUpButton() {
        return pickUpButton;
    }

    public Button getUseItButton() {
        return useItButton;
    }

    public Button getEnterButton() {
        return enterButton;
    }
}
