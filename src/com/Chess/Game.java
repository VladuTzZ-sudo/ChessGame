package com.Chess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Game {
    private static Game obj = null;
    int currentColor;
    int time;

    public Game(int color, int time) {
        this.currentColor = color;
        this.time = time;
        obj = this;
    }

    public static synchronized Game getInstance() {
        return obj;
    }

    public void play() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ChessInterface chessInterface = ChessInterface.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        move();
    }

    public void move() {
        Coordonate wCoordonate, bCoordonate;
        switch (currentColor) {
            case Colors.BLACK:
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    wCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(whitePiece.getX(), whitePiece.getY());
                    for (MouseListener listenerOnOff : wCoordonate.jPanel.getMouseListeners()) {
                        ((MouseListenerOnOff) listenerOnOff).setActive(false);
                    }
                }
                break;
            case Colors.WHITE:
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    bCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(blackPiece.getX(), blackPiece.getY());
                    for (MouseListener listenerOnOff : bCoordonate.jPanel.getMouseListeners()) {
                        ((MouseListenerOnOff) listenerOnOff).setActive(false);
                    }
                }
                break;
        }
    }

    public void nextMove() {
        Coordonate wCoordonate, bCoordonate;
        switch (currentColor) {
            case Colors.BLACK -> {
                System.out.println("Verify" + verifyChess());
                boolean isChess = false;
                if (verifyChess()) {
                    doIfChess();
                    isChess = true;
                }
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    wCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(whitePiece.getX(), whitePiece.getY());
                    for (MouseListener listenerOnOff : wCoordonate.jPanel.getMouseListeners()) {
                        ((MouseListenerOnOff) listenerOnOff).setActive(true);
                    }
                }
                currentColor = Colors.WHITE;
                move();
                if (isChess) {
                    if (checkmate()) {
                        ChessInterface.getInstance().doCheckMateAction();
                        System.out.println("sah mat");
                    } else {
                        ChessInterface.getInstance().makeFlagKingTrue();
                        System.out.println("Nu este sah mat");
                    }
                }
            }
            case Colors.WHITE -> {
                System.out.println("Verify" + verifyChess());
                boolean isChess = false;
                if (verifyChess()) {
                    doIfChess();
                    isChess = true;
                }
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    bCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(blackPiece.getX(), blackPiece.getY());
                    for (MouseListener listenerOnOff : bCoordonate.jPanel.getMouseListeners()) {
                        ((MouseListenerOnOff) listenerOnOff).setActive(true);
                    }
                }
                currentColor = Colors.BLACK;
                move();
                if (isChess) {
                    if (checkmate()) {
                        ChessInterface.getInstance().doCheckMateAction();
                        System.out.println("sah mat");
                    } else {
                        ChessInterface.getInstance().makeFlagKingTrue();
                        System.out.println("Nu este sah mat");
                    }
                }
            }
        }
    }

    public boolean verifyChess() {
        switch (currentColor) {
            case Colors.BLACK:
                Piece whiteKing = null;
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    if (whitePiece.getType().compareTo("King") == 0) {
                        whiteKing = whitePiece;
                    }
                }
                assert whiteKing != null;
                Coordonate whiteKingCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(whiteKing.getX(), whiteKing.getY());

                ArrayList<Coordonate> blackCoordonate = new ArrayList<>();
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    blackCoordonate.addAll(blackPiece.moves());
                }
                for (Coordonate coordonate : blackCoordonate) {
                    if (coordonate.X == whiteKingCoordonate.X && coordonate.Y == whiteKingCoordonate.Y) {
                        return true;
                    }
                }
                return false;
            case Colors.WHITE:
                Piece blackKing = null;
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    if (blackPiece.getType().compareTo("King") == 0) {
                        blackKing = blackPiece;
                    }
                }
                assert blackKing != null;
                Coordonate blackKingCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(blackKing.getX(), blackKing.getY());

                ArrayList<Coordonate> whiteCoordonate = new ArrayList<>();
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    whiteCoordonate.addAll(whitePiece.moves());
                }
                for (Coordonate coordonate : whiteCoordonate) {
                    if (coordonate.X == blackKingCoordonate.X && coordonate.Y == blackKingCoordonate.Y) {
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    public boolean stillChess() {
        switch (currentColor) {
            case Colors.BLACK:
                Piece blackKing = null;
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    if (blackPiece.getType().compareTo("King") == 0) {
                        blackKing = blackPiece;
                    }
                }
                assert blackKing != null;
                Coordonate blackKingCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(blackKing.getX(), blackKing.getY());

                ArrayList<Coordonate> whiteCoordonate = new ArrayList<>();
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    whiteCoordonate.addAll(whitePiece.moves());
                }
                for (Coordonate coordonate : whiteCoordonate) {
                    if (coordonate.X == blackKingCoordonate.X && coordonate.Y == blackKingCoordonate.Y) {
                        return true;
                    }
                }
                return false;
            default:
                return false;
            case Colors.WHITE:
                Piece whiteKing = null;
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    if (whitePiece.getType().compareTo("King") == 0) {
                        whiteKing = whitePiece;
                    }
                }
                assert whiteKing != null;
                Coordonate whiteKingCoordonate = ChessInterface.getInstance().getCoordonatefromLocation(whiteKing.getX(), whiteKing.getY());

                ArrayList<Coordonate> blackCoordonate = new ArrayList<>();
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    blackCoordonate.addAll(blackPiece.moves());
                }
                for (Coordonate coordonate : blackCoordonate) {
                    if (coordonate.X == whiteKingCoordonate.X && coordonate.Y == whiteKingCoordonate.Y) {
                        return true;
                    }
                }
                return false;
        }
    }

    public void doIfChess() {
        switch (currentColor) {
            case Colors.BLACK -> {
                Coordonate kingWhite = ChessInterface.getInstance().getCoordonatefromLocation(Whites.getInstance().returnPieceByType("King").getX(), Whites.getInstance().returnPieceByType("King").getY());
                kingWhite.jPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                kingWhite.jPanel.validate();
                kingWhite.jPanel.revalidate();
            }
            case Colors.WHITE -> {
                Coordonate kingBlack = ChessInterface.getInstance().getCoordonatefromLocation(Blacks.getInstance().returnPieceByType("King").getX(), Blacks.getInstance().returnPieceByType("King").getY());
                kingBlack.jPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                kingBlack.jPanel.validate();
                kingBlack.jPanel.revalidate();
            }
        }
    }

    public void doAfterChess() {
        Coordonate kingWhite = ChessInterface.getInstance().getCoordonatefromLocation(Whites.getInstance().returnPieceByType("King").getX(), Whites.getInstance().returnPieceByType("King").getY());
        kingWhite.jPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        kingWhite.jPanel.validate();
        kingWhite.jPanel.revalidate();
        Coordonate kingBlack = ChessInterface.getInstance().getCoordonatefromLocation(Blacks.getInstance().returnPieceByType("King").getX(), Blacks.getInstance().returnPieceByType("King").getY());
        kingBlack.jPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        kingBlack.jPanel.validate();
        kingBlack.jPanel.revalidate();
    }

    public boolean checkmate() {
        switch (currentColor) {
            case Colors.BLACK:
                boolean verify = false;
                for (Piece blackPiece : Blacks.getInstance().pieces) {
                    Coordonate lastCoordonate;
                    Coordonate coordonate = ChessInterface.getInstance().getCoordonatefromLocation(blackPiece.getX(), blackPiece.getY());
                    Piece piece = ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y);
                    ArrayList<Coordonate> lastCoordonates = new ArrayList<>();
                    lastCoordonates = piece.moves();
                    lastCoordonate = coordonate;
                    for (Coordonate prefire : lastCoordonates) {
                        if (prefire.jPanel.getComponentCount() == 0) {
                            coordonate = prefire;
                            coordonate.jPanel.add(lastCoordonate.jPanel.getComponent(0));
                            ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).move(coordonate.X, coordonate.Y);
                            ChessBoard.getInstance().changePieceCoordonates(lastCoordonate, coordonate);
                            if (!Game.getInstance().stillChess()) {
                                verify = true;
                            }
                            lastCoordonate.jPanel.add(coordonate.jPanel.getComponent(0));
                            coordonate.jPanel.removeAll();
                            coordonate.jPanel.repaint();
                            coordonate.jPanel.revalidate();
                            ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y).move(lastCoordonate.X, lastCoordonate.Y);
                            ChessBoard.getInstance().changePieceCoordonates(coordonate, lastCoordonate);
                            if (verify) {
                                break;
                            }
                        } else {
                            coordonate = prefire;
                            if (ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).Color() != ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y).Color()) {
                                Piece forRemove = ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y);
                                Piece forReplace = ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y);

                                Component comlastCoordonate = lastCoordonate.jPanel.getComponent(0);
                                Component comCoordonate = coordonate.jPanel.getComponent(0);

                                ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).move(coordonate.X, coordonate.Y);
                                ChessBoard.getInstance().changePieceCoordonates(lastCoordonate, coordonate);

                                coordonate.jPanel.removeAll();
                                coordonate.jPanel.add(lastCoordonate.jPanel.getComponent(0));
                                coordonate.jPanel.revalidate();
                                lastCoordonate.jPanel.removeAll();
                                lastCoordonate.jPanel.repaint();
                                lastCoordonate.jPanel.revalidate();
                                if (forRemove.Color() == Colors.WHITE) {
                                    Whites.getInstance().pieces.remove(forRemove);
                                    Whites.getInstance().remaining--;
                                } else {
                                    Blacks.getInstance().pieces.remove(forRemove);
                                    Blacks.getInstance().remaining--;
                                }
                                if (!Game.getInstance().stillChess()) {
                                    verify = true;
                                }
                                coordonate.jPanel.removeAll();
                                coordonate.jPanel.add(comCoordonate);
                                lastCoordonate.jPanel.add(comlastCoordonate);
                                lastCoordonate.jPanel.revalidate();
                                coordonate.jPanel.revalidate();

                                if (forRemove.Color() == Colors.WHITE) {
                                    Whites.getInstance().pieces.add(forRemove);
                                    Whites.getInstance().remaining++;
                                } else {
                                    Blacks.getInstance().pieces.add(forRemove);
                                    Blacks.getInstance().remaining++;
                                }

                                forRemove.move(coordonate.X, coordonate.Y);
                                forReplace.move(lastCoordonate.X, lastCoordonate.Y);
                                ChessBoard.getInstance().changePieceCoordonates(coordonate, lastCoordonate);
                                ChessBoard.getInstance().table[coordonate.X][coordonate.Y] = forRemove;
                                if (verify) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (verify) {
                    return false;
                }
                break;
            case Colors.WHITE:
                verify = false;
                for (Piece whitePiece : Whites.getInstance().pieces) {
                    Coordonate lastCoordonate;
                    Coordonate coordonate = ChessInterface.getInstance().getCoordonatefromLocation(whitePiece.getX(), whitePiece.getY());
                    Piece piece = ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y);
                    ArrayList<Coordonate> lastCoordonates = new ArrayList<>();
                    lastCoordonates = piece.moves();
                    lastCoordonate = coordonate;
                    for (Coordonate prefire : lastCoordonates) {
                        if (prefire.jPanel.getComponentCount() == 0) {
                            coordonate = prefire;
                            coordonate.jPanel.add(lastCoordonate.jPanel.getComponent(0));
                            ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).move(coordonate.X, coordonate.Y);
                            ChessBoard.getInstance().changePieceCoordonates(lastCoordonate, coordonate);
                            if (!Game.getInstance().stillChess()) {
                                verify = true;
                            }
                            lastCoordonate.jPanel.add(coordonate.jPanel.getComponent(0));
                            coordonate.jPanel.removeAll();
                            coordonate.jPanel.repaint();
                            coordonate.jPanel.revalidate();
                            ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y).move(lastCoordonate.X, lastCoordonate.Y);
                            ChessBoard.getInstance().changePieceCoordonates(coordonate, lastCoordonate);
                            if (verify) {
                                break;
                            }
                        } else {
                            coordonate = prefire;
                            if (ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).Color() != ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y).Color()) {
                                Piece forRemove = ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y);
                                Piece forReplace = ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y);

                                Component comlastCoordonate = lastCoordonate.jPanel.getComponent(0);
                                Component comCoordonate = coordonate.jPanel.getComponent(0);

                                ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).move(coordonate.X, coordonate.Y);
                                ChessBoard.getInstance().changePieceCoordonates(lastCoordonate, coordonate);

                                coordonate.jPanel.removeAll();
                                coordonate.jPanel.add(lastCoordonate.jPanel.getComponent(0));
                                coordonate.jPanel.revalidate();
                                lastCoordonate.jPanel.removeAll();
                                lastCoordonate.jPanel.repaint();
                                lastCoordonate.jPanel.revalidate();
                                if (forRemove.Color() == Colors.WHITE) {
                                    Whites.getInstance().pieces.remove(forRemove);
                                    Whites.getInstance().remaining--;
                                } else {
                                    Blacks.getInstance().pieces.remove(forRemove);
                                    Blacks.getInstance().remaining--;
                                }
                                if (!Game.getInstance().stillChess()) {
                                    verify = true;
                                }
                                coordonate.jPanel.removeAll();
                                coordonate.jPanel.add(comCoordonate);
                                lastCoordonate.jPanel.add(comlastCoordonate);
                                lastCoordonate.jPanel.revalidate();
                                coordonate.jPanel.revalidate();

                                if (forRemove.Color() == Colors.WHITE) {
                                    Whites.getInstance().pieces.add(forRemove);
                                    Whites.getInstance().remaining++;
                                } else {
                                    Blacks.getInstance().pieces.add(forRemove);
                                    Blacks.getInstance().remaining++;
                                }

                                forRemove.move(coordonate.X, coordonate.Y);
                                forReplace.move(lastCoordonate.X, lastCoordonate.Y);
                                ChessBoard.getInstance().changePieceCoordonates(coordonate, lastCoordonate);
                                ChessBoard.getInstance().table[coordonate.X][coordonate.Y] = forRemove;
                                if (verify) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (verify) {
                    return false;
                }
                break;
        }
        return true;
    }
}

