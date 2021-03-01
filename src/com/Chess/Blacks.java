package com.Chess;

import java.util.ArrayList;

public class Blacks {
    int remaining;
    ArrayList<Piece> pieces;
    private static Blacks obj = null;

    public static synchronized Blacks getInstance() {
        if (obj == null) {
            obj = new Blacks();
        }
        return obj;
    }

    public Blacks(){
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
