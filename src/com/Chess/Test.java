package com.Chess;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        start();
    }

    public static void start(){
        Blacks blacks = Blacks.getInstance();
        Whites whites = Whites.getInstance();

        ChessBoard.ChessBoardBuilder chessBoardBuilder = new ChessBoard.ChessBoardBuilder();
        chessBoardBuilder.chooseClassic();
        ChessBoard chessBoard = chessBoardBuilder.build();

        Game game = new Game(Colors.WHITE, 10);
        game.play();
    }
}
