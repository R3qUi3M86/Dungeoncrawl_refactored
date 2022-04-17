package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.display.cells.Cell;

public class SignPost extends Decor {
    public SignPost(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return DecorType.SIGN_POST.toString();
    }

    @Override
    public DecorType getDecorType() {
        return DecorType.SIGN_POST;
    }
}
