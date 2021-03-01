package com.Chess;

import com.Chess.Piece;

import javax.swing.*;
import java.util.ArrayList;

public class Rock extends Piece {
    int X;
    int Y;
    int color;

    public Rock(int X, int Y, int color) {
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    @Override
    public ArrayList<Coordonate> moves() {
        ArrayList<Coordonate> coordonates = new ArrayList<>();
        int x = X;
        int y = Y;
        x--;
        while (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
            if (ChessInterface.getInstance().getCoordonatefromLocation(x, y).jPanel.getComponentCount() != 0) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(x, y).Color() == color){
                    break;
                }else {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
                    break;
                }
            }
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
            x--;
        }
        x = X;
        y = Y;
        x++;
        while (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
            if (ChessInterface.getInstance().getCoordonatefromLocation(x, y).jPanel.getComponentCount() != 0) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(x, y).Color() == color){
                    break;
                }else {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
                    break;
                }
            }
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
            x++;
        }
        x = X;
        y = Y;
        y++;
        while (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
            if (ChessInterface.getInstance().getCoordonatefromLocation(x, y).jPanel.getComponentCount() != 0) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(x, y).Color() == color){
                    break;
                }else {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
                    break;
                }
            }
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
            y++;
        }
        x = X;
        y = Y;
        y--;
        while (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
            if (ChessInterface.getInstance().getCoordonatefromLocation(x, y).jPanel.getComponentCount() != 0) {
                if (ChessBoard.getInstance().getPiecebyCoordonates(x, y).Color() == color){
                    break;
                }else {
                    coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
                    break;
                }
            }
            coordonates.add(ChessInterface.getInstance().getCoordonatefromLocation(x, y));
            y--;
        }
        return coordonates;
    }

    @Override
    public String getType() {
        return "Rock";
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
