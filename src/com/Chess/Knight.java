package com.Chess;

import javax.swing.*;
import java.util.ArrayList;

public class Knight extends Piece {
    int X;
    int Y;
    int color;

    public Knight(int X, int Y, int color) {
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    @Override
    public ArrayList<Coordonate> moves() {
        ArrayList<Coordonate> coordonates = new ArrayList<>();
        if (X - 2 >= 0 && Y + 1 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X - 2, Y + 1));
        }
        if (X - 2 >= 0 && Y - 1 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X - 2, Y - 1));
        }
        if (X - 1 >= 0 && Y + 2 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X - 1, Y + 2));
        }
        if (X + 1 <= 7 && Y + 2 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X + 1, Y + 2));
        }
        if (X + 2 <= 7 && Y + 1 <= 7) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X + 2, Y + 1));
        }
        if (X + 2 <= 7 && Y - 1 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X + 2, Y - 1));
        }
        if (X + 1 <= 7 && Y - 2 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X + 1, Y - 2));
        }
        if (X - 1 >= 0 && Y - 2 >= 0) {
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X - 1, Y - 2));
        }
        return coordonates;
    }

    @Override
    public String getType() {
        return "Knight";
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
