package com.Chess;

import java.util.ArrayList;

public class Whites {
    int remaining;
    ArrayList<Piece> pieces;
    private static Whites obj = null;

    public static synchronized Whites getInstance() {
        if (obj == null) {
            obj = new Whites();
        }
        return obj;
    }

    public Whites(){
        pieces = new ArrayList<>(16);
        remaining = 0;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
        remaining++;
    }

    public Piece returnPieceByType(String name){
        for (Piece piece : pieces){
            if (piece.getType().compareTo(name) == 0){
                return piece;
            }
        }
        return null;
    }
}
