package com.Chess;

import com.Chess.Piece;

import javax.swing.*;
import java.util.ArrayList;

public class Queen extends Piece {
    int X;
    int Y;
    int color;

    public Queen(int X, int Y,int color){
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    @Override
    public ArrayList<Coordonate> moves() {
        Rock rock = new Rock(X,Y,color);
        Bishop bishop = new Bishop(X,Y,color);
        ArrayList<Coordonate> coordonates1 = rock.moves();
        ArrayList<Coordonate> coordonates2 = bishop.moves();
        coordonates1.addAll(coordonates2);
        return coordonates1;
    }

    @Override
    public String getType() {
        return "Queen";
    }

    @Override
    public int Color() {
        if ( color == Colors.BLACK) {
            return Colors.BLACK;
        }else{
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
