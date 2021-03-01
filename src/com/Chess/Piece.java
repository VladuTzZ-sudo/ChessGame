package com.Chess;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Piece {

    public abstract ArrayList<Coordonate> moves();

    public abstract String getType();

    public abstract int Color();

    public abstract void move(int X, int Y);

    public abstract int getX();

    public abstract int getY();

    @Override
    public String toString() {
        if (Color() == 1) {
            return getType() + "-Black";
        } else {
            return getType() + "-White";
        }
    }
}
