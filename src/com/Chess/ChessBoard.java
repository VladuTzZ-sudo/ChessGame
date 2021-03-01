package com.Chess;

import java.util.ArrayList;

public class ChessBoard {
    private static ChessBoard obj = null;
    Piece[][] table;

    private ChessBoard() {
        table = new Piece[8][8];
    }

    public static synchronized ChessBoard getInstance() {
        if (obj == null) {
            ChessBoard.ChessBoardBuilder chessBoardBuilder = new ChessBoard.ChessBoardBuilder();
            chessBoardBuilder.chooseClassic();
            obj = chessBoardBuilder.build();
        }
        return obj;
    }

    public ChessBoard(ChessBoardBuilder boardBuilder){
        this.table = boardBuilder.table;
    }

    public Piece getPiecebyCoordonates(int X, int Y){
        return table[X][Y];
    }

    public void changePieceCoordonates(Coordonate wherewas, Coordonate whereis){
        table[whereis.X][whereis.Y] = table[wherewas.X][wherewas.Y];
        table[wherewas.X][wherewas.Y] = null;
    }

    public static class ChessBoardBuilder {
        Piece[][] table;

        public ChessBoardBuilder() {
            table = new Piece[8][8];
        }

        public void chooseClassic() {
            Whites whites = Whites.getInstance();
            Blacks blacks = Blacks.getInstance();
            //ALL Pawns
            ArrayList<Pawn> pawnsW = new ArrayList<>();
            ArrayList<Pawn> pawnsB = new ArrayList<>();
            for (int i = 0; i <= 7; i++) {
                pawnsW.add(i, new Pawn(i, 1, Colors.WHITE));
                pawnsB.add(i, new Pawn(i, 6, Colors.BLACK));
                table[i][1] = pawnsW.get(i);
                table[i][6] = pawnsB.get(i);
                whites.addPiece(pawnsW.get(i));
                blacks.addPiece(pawnsB.get(i));
            }
            //ALL Rocks
            ArrayList<Rock> rockssW = new ArrayList<>(2);
            ArrayList<Rock> rockssB = new ArrayList<>(2);
            rockssW.add(0, new Rock(0, 0, Colors.WHITE));
            rockssW.add(1, new Rock(7, 0, Colors.WHITE));
            table[0][0] = rockssW.get(0);
            table[7][0] = rockssW.get(1);
            whites.addPiece(rockssW.get(0));
            whites.addPiece(rockssW.get(1));

            rockssB.add(0, new Rock(0, 7, Colors.BLACK));
            rockssB.add(1, new Rock(7, 7, Colors.BLACK));
            table[0][7] = rockssB.get(0);
            table[7][7] = rockssB.get(1);
            blacks.addPiece(rockssB.get(0));
            blacks.addPiece(rockssB.get(1));
            //ALL Knights
            ArrayList<Knight> knightsW = new ArrayList<>(2);
            ArrayList<Knight> knightsB = new ArrayList<>(2);
            knightsW.add(0, new Knight(1, 0, Colors.WHITE));
            knightsW.add(1, new Knight(6, 0, Colors.WHITE));
            table[1][0] = knightsW.get(0);
            table[6][0] = knightsW.get(1);
            whites.addPiece(knightsW.get(0));
            whites.addPiece(knightsW.get(1));

            knightsB.add(0, new Knight(1, 7, Colors.BLACK));
            knightsB.add(1, new Knight(6, 7, Colors.BLACK));
            table[1][7] = knightsB.get(0);
            table[6][7] = knightsB.get(1);
            blacks.addPiece(knightsB.get(0));
            blacks.addPiece(knightsB.get(1));
            //ALL Bishops
            ArrayList<Bishop> bishopsW = new ArrayList<>(2);
            ArrayList<Bishop> bishopsB = new ArrayList<>(2);
            bishopsW.add(0, new Bishop(2, 0, Colors.WHITE));
            bishopsW.add(1, new Bishop(5, 0, Colors.WHITE));
            table[2][0] = bishopsW.get(0);
            table[5][0] = bishopsW.get(1);
            whites.addPiece(bishopsW.get(0));
            whites.addPiece(bishopsW.get(1));

            bishopsB.add(0, new Bishop(2, 7, Colors.BLACK));
            bishopsB.add(1, new Bishop(5, 7, Colors.BLACK));
            table[2][7] = bishopsB.get(0);
            table[5][7] = bishopsB.get(1);
            blacks.addPiece(bishopsB.get(0));
            blacks.addPiece(bishopsB.get(1));
            //Queens
            Queen queenW = new Queen(3, 0, Colors.WHITE);
            table[3][0] = queenW;
            whites.addPiece(queenW);

            Queen queenB = new Queen(3, 7, Colors.BLACK);
            table[3][7] = queenB;
            blacks.addPiece(queenB);
            //Knigs
            King kingW = new King(4, 0, Colors.WHITE);
            table[4][0] = kingW;
            whites.addPiece(kingW);

            King kingB = new King(4, 7, Colors.BLACK);
            table[4][7] = kingB;
            blacks.addPiece(kingB);
        }

        public com.Chess.ChessBoard build(){
            obj = new com.Chess.ChessBoard(this);
            return obj;
        }
    }
}
