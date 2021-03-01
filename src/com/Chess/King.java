package com.Chess;

import javax.swing.*;
import java.util.ArrayList;

public class King extends Piece {
    int X;
    int Y;
    int color;

    public King(int X, int Y, int color) {
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    @Override
    public ArrayList<Coordonate> moves() {
        ArrayList<Coordonate> coordonates = new ArrayList<>();
        int x = X;
        int y = Y;
        if (x + 1 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x + 1, y));
        }
        if (x - 1 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x - 1, y));
        }
        if (y + 1 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y + 1));
        }
        if (y - 1 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y - 1));
        }
        if (x + 1 <= 7 && y + 1 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x + 1, y + 1));
        }
        if (x + 1 <= 7 && y - 1 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x + 1, y - 1));
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x - 1, y - 1));
        }
        if (x - 1 >= 0 && y + 1 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x - 1, y + 1));
        }
        return coordonates;
    }

    @Override
    public String getType() {
        return "King";
    }

    @Override
    public int Color() {
        if (color == Colors.BLACK) {
            return Colors.BLACK;
        } else {
            return Colors.WHITE;
        }
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public void move(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}
