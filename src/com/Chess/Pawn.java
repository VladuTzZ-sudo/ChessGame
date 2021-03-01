package com.Chess;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pawn extends Piece {
    int X;
    int Y;
    int color;

    public Pawn(int X, int Y, int color) {
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    @Override
    public ArrayList<Coordonate> moves() {
        ArrayList<Coordonate> coordonates = new ArrayList<>();
        if (color == Colors.WHITE) {
            if (Y + 1 <= 7) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(X, Y + 1) == null) {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X, Y + 1));
                }
            }
            if (X + 1 <= 7 && Y + 1 <= 7) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(X + 1, Y + 1) != null) {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X + 1, Y + 1));
                }
            }
            if (X - 1 >= 0 && Y + 1 <= 7) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(X - 1, Y + 1) != null) {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X - 1, Y + 1));
                }
            }
        }else {
            if (Y - 1 >= 0) {
                coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X, Y - 1));
            }
            if (X + 1 <= 7 && Y - 1 >= 0) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(X + 1, Y - 1) != null) {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X + 1, Y - 1));
                }
            }
            if (X - 1 >= 0 && Y - 1 >= 0) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(X - 1, Y - 1) != null) {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(X - 1, Y - 1));
                }
            }
        }
        return coordonates;
    }

    @Override
    public String getType() {
        return "Pawn";
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
    public int Color() {
        if (color == Colors.BLACK) {
            return Colors.BLACK;
        } else {
            return Colors.WHITE;
        }
    }

    @Override
    public void move(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}
