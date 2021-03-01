package com.Chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChessInterface extends JFrame {
    private static ChessInterface obj = null;
    private JPanel MainPage;
    private JPanel ChessTable;
    public ArrayList<Coordonate> coordonates;
    private ArrayList<Coordonate> lastCoordonates;
    private JPanel zeroseven;
    private JPanel oneseven;
    private JPanel twoseven;
    private JPanel threeseven;
    private JPanel fourseven;
    private JPanel fiveseven;
    private JPanel sixseven;
    private JPanel zerozero;
    private JPanel onezero;
    private JPanel twozero;
    private JPanel threezero;
    private JPanel fourzero;
    private JPanel fivezero;
    private JPanel sixzero;
    private JPanel zeroone;
    private JPanel oneone;
    private JPanel twoone;
    private JPanel threeone;
    private JPanel fourone;
    private JPanel fiveone;
    private JPanel sixone;
    private JPanel zerotwo;
    private JPanel onetwo;
    private JPanel twotwo;
    private JPanel threetwo;
    private JPanel fourtwo;
    private JPanel fivetwo;
    private JPanel sixtwo;
    private JPanel zerothree;
    private JPanel onethree;
    private JPanel twothree;
    private JPanel threethree;
    private JPanel fourthree;
    private JPanel fivethree;
    private JPanel sixthree;
    private JPanel zerofour;
    private JPanel onefour;
    private JPanel twofour;
    private JPanel threefour;
    private JPanel fourfour;
    private JPanel fivefour;
    private JPanel sixfour;
    private JPanel zerofive;
    private JPanel onefive;
    private JPanel twofive;
    private JPanel threefive;
    private JPanel fourfive;
    private JPanel fivefive;
    private JPanel sixfive;
    private JPanel zerosix;
    private JPanel onesix;
    private JPanel twosix;
    private JPanel threesix;
    private JPanel foursix;
    private JPanel fivesix;
    private JPanel sixsix;
    private JPanel sevensix;
    private JPanel sevenfive;
    private JPanel sevenseven;
    private JPanel sevenzero;
    private JPanel sevenone;
    private JPanel seventwo;
    private JPanel seventhree;
    private JPanel sevenfour;
    private JPanel MainPanel;
    private JSplitPane SplitPane;
    private JSplitPane ButtonPane;
    private JButton NewGame;
    private JButton TextButton;
    private JPanel TextPanel;
    private Coordonate lastCoordonate;
    private boolean isClickedforTarget = false;
    public boolean afterChessEliminateBorder = false;

    public static synchronized ChessInterface getInstance() {
        if (obj == null) {
            obj = new ChessInterface("Chees Game");
        }
        return obj;
    }

    public ChessInterface(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        ChessTable.setMaximumSize(new Dimension(400, 400));
        ChessTable.setPreferredSize(new Dimension(400, 400));
        ChessTable.setMinimumSize(new Dimension(400, 400));

        for (Component component : ChessTable.getComponents()) {
            component.setSize(new Dimension(50, 50));
            component.setPreferredSize(new Dimension(50, 50));
            component.setMaximumSize(new Dimension(50, 50));
            component.setMinimumSize(new Dimension(50, 50));
        }

        SplitPane.setEnabled(false);
        SplitPane.setDividerLocation(40);
        SplitPane.setRightComponent(MainPanel);
        ButtonPane.setPreferredSize(new Dimension(SplitPane.getLeftComponent().getWidth(), SplitPane.getLeftComponent().getHeight()));
        SplitPane.setLeftComponent(ButtonPane);
        ButtonPane.setEnabled(false);
        ButtonPane.setDividerLocation(120);
        TextButton.setFocusable(false);
        TextButton.setEnabled(false);
        NewGame.setFocusable(false);

        createCoordonates();
        createTable();
        addactions();

        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = new String[0]; // Or String[] args = {};
                obj.dispose();
                obj = null;
                Test.main(args);
                //Test.start();
                //Test.start();
            }
        });

        setContentPane(MainPage);
        setVisible(true);
        pack();
    }

    public void makeFlagKingTrue(){
        afterChessEliminateBorder = true;
    }

    private void addactions() {
        for (Coordonate coordonate : coordonates) {
            MouseListenerOnOff thisAction = new MouseListenerOnOff() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!getActive() && !isClickedforTarget) {
                        return;
                    }
                    if (!isClickedforTarget) {
                        Component[] components = coordonate.jPanel.getComponents();
                        for (Component component : components) {
                            if (component.getClass().equals(JLabel.class)) {
                                Piece piece = ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y);
                                lastCoordonates = piece.moves();
                                for (Coordonate prefire : lastCoordonates) {
                                    if (prefire.jPanel.getComponentCount() == 0) {
                                        prefire.jPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                                        isClickedforTarget = true;
                                        lastCoordonate = coordonate;
                                        prefire.jPanel.validate();
                                        prefire.jPanel.revalidate();
                                    } else {
                                        if (ChessBoard.getInstance().getPiecebyCoordonates(prefire.X, prefire.Y).Color() != ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y).Color()) {
                                            prefire.jPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
                                            isClickedforTarget = true;
                                            lastCoordonate = coordonate;
                                            prefire.jPanel.validate();
                                            prefire.jPanel.revalidate();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if(afterChessEliminateBorder){
                            Game.getInstance().doAfterChess();
                            afterChessEliminateBorder = false;
                        }
                        for (Coordonate prefire : lastCoordonates) {
                            if (prefire.jPanel.getComponentCount() == 0) {
                                if (coordonate.X == prefire.X && coordonate.Y == prefire.Y) {
                                    coordonate.jPanel.add(lastCoordonate.jPanel.getComponent(0));
                                    ChessBoard.getInstance().getPiecebyCoordonates(lastCoordonate.X, lastCoordonate.Y).move(coordonate.X, coordonate.Y);
                                    ChessBoard.getInstance().changePieceCoordonates(lastCoordonate, coordonate);
                                    if (Game.getInstance().stillChess()) {
                                        lastCoordonate.jPanel.add(coordonate.jPanel.getComponent(0));
                                        coordonate.jPanel.removeAll();
                                        coordonate.jPanel.repaint();
                                        coordonate.jPanel.revalidate();
                                        ChessBoard.getInstance().getPiecebyCoordonates(coordonate.X, coordonate.Y).move(lastCoordonate.X, lastCoordonate.Y);
                                        ChessBoard.getInstance().changePieceCoordonates(coordonate, lastCoordonate);
                                    } else {
                                        lastCoordonate.jPanel.removeAll();
                                        lastCoordonate.jPanel.repaint();
                                        lastCoordonate.jPanel.revalidate();
                                        Game.getInstance().nextMove();
                                    }
                                }
                            } else {
                                if (coordonate.X == prefire.X && coordonate.Y == prefire.Y) {
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
                                        if (Game.getInstance().stillChess()) {
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
                                        }else{
                                            Game.getInstance().nextMove();
                                        }
                                    }
                                }
                            }
                            //if (Game.getInstance().verifyChess()) {
                            //    Game.getInstance().doIfChess();
                            //}
                            prefire.jPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
                            prefire.jPanel.validate();
                            prefire.jPanel.revalidate();
                        }
                        isClickedforTarget = false;
                    }
                }

            };
            coordonate.action = thisAction;
            coordonate.jPanel.addMouseListener(thisAction);
        }
    }

    private void createTable() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("src/com/Chess/white_queen.jpg"));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JLabel whiteQueen = new JLabel(imageIcon);
            whiteQueen.setPreferredSize(twotwo.getPreferredSize());

            myPicture = ImageIO.read(new File("src/com/Chess/target.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JLabel target = new JLabel(imageIcon);
            target.setPreferredSize(twotwo.getPreferredSize());

            myPicture = ImageIO.read(new File("src/com/Chess/black_queen.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JLabel blackQueen = new JLabel(imageIcon);
            blackQueen.setPreferredSize(twotwo.getPreferredSize());

            myPicture = ImageIO.read(new File("src/com/Chess/black_king.jpg"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JLabel blackKing = new JLabel(imageIcon);
            blackKing.setPreferredSize(twotwo.getPreferredSize());

            myPicture = ImageIO.read(new File("src/com/Chess/white_king.jpg"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JLabel whiteKing = new JLabel(imageIcon);
            whiteKing.setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> blackBishops = new ArrayList<>(2);
            myPicture = ImageIO.read(new File("src/com/Chess/black_bishop.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            blackBishops.add(0, new JLabel(imageIcon));
            blackBishops.get(0).setPreferredSize(twotwo.getPreferredSize());
            blackBishops.add(1, new JLabel(imageIcon));
            blackBishops.get(1).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> whiteBishops = new ArrayList<>(2);
            myPicture = ImageIO.read(new File("src/com/Chess/white_bishop.jpg"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            whiteBishops.add(0, new JLabel(imageIcon));
            whiteBishops.get(0).setPreferredSize(twotwo.getPreferredSize());
            whiteBishops.add(1, new JLabel(imageIcon));
            whiteBishops.get(1).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> blackKnights = new ArrayList<>(2);
            myPicture = ImageIO.read(new File("src/com/Chess/black_knight.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            blackKnights.add(0, new JLabel(imageIcon));
            blackKnights.get(0).setPreferredSize(twotwo.getPreferredSize());
            blackKnights.add(1, new JLabel(imageIcon));
            blackKnights.get(1).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> whiteKnights = new ArrayList<>(2);
            myPicture = ImageIO.read(new File("src/com/Chess/wh_cal.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            whiteKnights.add(0, new JLabel(imageIcon));
            whiteKnights.get(0).setPreferredSize(twotwo.getPreferredSize());
            whiteKnights.add(1, new JLabel(imageIcon));
            whiteKnights.get(1).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> blackRooks = new ArrayList<>(2);
            myPicture = ImageIO.read(new File("src/com/Chess/black_rook.jpg"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            blackRooks.add(0, new JLabel(imageIcon));
            blackRooks.get(0).setPreferredSize(twotwo.getPreferredSize());
            blackRooks.add(1, new JLabel(imageIcon));
            blackRooks.get(1).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> whiteRooks = new ArrayList<>(2);
            myPicture = ImageIO.read(new File("src/com/Chess/white_rock.jpg"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            whiteRooks.add(0, new JLabel(imageIcon));
            whiteRooks.get(0).setPreferredSize(twotwo.getPreferredSize());
            whiteRooks.add(1, new JLabel(imageIcon));
            whiteRooks.get(1).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> blackPawns = new ArrayList<>(8);
            myPicture = ImageIO.read(new File("src/com/Chess/black_pawn.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            blackPawns.add(0, new JLabel(imageIcon));
            blackPawns.get(0).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(1, new JLabel(imageIcon));
            blackPawns.get(1).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(2, new JLabel(imageIcon));
            blackPawns.get(2).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(3, new JLabel(imageIcon));
            blackPawns.get(3).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(4, new JLabel(imageIcon));
            blackPawns.get(4).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(5, new JLabel(imageIcon));
            blackPawns.get(5).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(6, new JLabel(imageIcon));
            blackPawns.get(6).setPreferredSize(twotwo.getPreferredSize());
            blackPawns.add(7, new JLabel(imageIcon));
            blackPawns.get(7).setPreferredSize(twotwo.getPreferredSize());

            ArrayList<JLabel> whitePawns = new ArrayList<>(8);
            myPicture = ImageIO.read(new File("src/com/Chess/white_pawn.png"));
            imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            whitePawns.add(0, new JLabel(imageIcon));
            whitePawns.get(0).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(1, new JLabel(imageIcon));
            whitePawns.get(1).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(2, new JLabel(imageIcon));
            whitePawns.get(2).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(3, new JLabel(imageIcon));
            whitePawns.get(3).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(4, new JLabel(imageIcon));
            whitePawns.get(4).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(5, new JLabel(imageIcon));
            whitePawns.get(5).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(6, new JLabel(imageIcon));
            whitePawns.get(6).setPreferredSize(twotwo.getPreferredSize());
            whitePawns.add(7, new JLabel(imageIcon));
            whitePawns.get(7).setPreferredSize(twotwo.getPreferredSize());

            threezero.add(whiteQueen);
            threeseven.add(blackQueen);
            fourzero.add(whiteKing);
            fourseven.add(blackKing);
            twoseven.add(blackBishops.get(0));
            fiveseven.add(blackBishops.get(1));
            twozero.add(whiteBishops.get(0));
            fivezero.add(whiteBishops.get(1));
            oneseven.add(blackKnights.get(0));
            sixseven.add(blackKnights.get(1));
            onezero.add(whiteKnights.get(0));
            sixzero.add(whiteKnights.get(1));
            zerozero.add(whiteRooks.get(0));
            sevenzero.add(whiteRooks.get(1));
            zeroseven.add(blackRooks.get(0));
            sevenseven.add(blackRooks.get(1));

            zeroone.add(whitePawns.get(0));
            oneone.add(whitePawns.get(1));
            twoone.add(whitePawns.get(2));
            threeone.add(whitePawns.get(3));
            fourone.add(whitePawns.get(4));
            fiveone.add(whitePawns.get(5));
            sixone.add(whitePawns.get(6));
            sevenone.add(whitePawns.get(7));

            zerosix.add(blackPawns.get(0));
            onesix.add(blackPawns.get(1));
            twosix.add(blackPawns.get(2));
            threesix.add(blackPawns.get(3));
            foursix.add(blackPawns.get(4));
            fivesix.add(blackPawns.get(5));
            sixsix.add(blackPawns.get(6));
            sevensix.add(blackPawns.get(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCoordonates() {
        coordonates = new ArrayList<>();
        coordonates.add(new Coordonate(zerozero, 0, 0));
        coordonates.add(new Coordonate(zeroone, 0, 1));
        coordonates.add(new Coordonate(zerotwo, 0, 2));
        coordonates.add(new Coordonate(zerothree, 0, 3));
        coordonates.add(new Coordonate(zerofour, 0, 4));
        coordonates.add(new Coordonate(zerofive, 0, 5));
        coordonates.add(new Coordonate(zerosix, 0, 6));
        coordonates.add(new Coordonate(zeroseven, 0, 7));

        coordonates.add(new Coordonate(onezero, 1, 0));
        coordonates.add(new Coordonate(oneone, 1, 1));
        coordonates.add(new Coordonate(onetwo, 1, 2));
        coordonates.add(new Coordonate(onethree, 1, 3));
        coordonates.add(new Coordonate(onefour, 1, 4));
        coordonates.add(new Coordonate(onefive, 1, 5));
        coordonates.add(new Coordonate(onesix, 1, 6));
        coordonates.add(new Coordonate(oneseven, 1, 7));

        coordonates.add(new Coordonate(twozero, 2, 0));
        coordonates.add(new Coordonate(twoone, 2, 1));
        coordonates.add(new Coordonate(twotwo, 2, 2));
        coordonates.add(new Coordonate(twothree, 2, 3));
        coordonates.add(new Coordonate(twofour, 2, 4));
        coordonates.add(new Coordonate(twofive, 2, 5));
        coordonates.add(new Coordonate(twosix, 2, 6));
        coordonates.add(new Coordonate(twoseven, 2, 7));

        coordonates.add(new Coordonate(threezero, 3, 0));
        coordonates.add(new Coordonate(threeone, 3, 1));
        coordonates.add(new Coordonate(threetwo, 3, 2));
        coordonates.add(new Coordonate(threethree, 3, 3));
        coordonates.add(new Coordonate(threefour, 3, 4));
        coordonates.add(new Coordonate(threefive, 3, 5));
        coordonates.add(new Coordonate(threesix, 3, 6));
        coordonates.add(new Coordonate(threeseven, 3, 7));

        coordonates.add(new Coordonate(fourzero, 4, 0));
        coordonates.add(new Coordonate(fourone, 4, 1));
        coordonates.add(new Coordonate(fourtwo, 4, 2));
        coordonates.add(new Coordonate(fourthree, 4, 3));
        coordonates.add(new Coordonate(fourfour, 4, 4));
        coordonates.add(new Coordonate(fourfive, 4, 5));
        coordonates.add(new Coordonate(foursix, 4, 6));
        coordonates.add(new Coordonate(fourseven, 4, 7));

        coordonates.add(new Coordonate(fivezero, 5, 0));
        coordonates.add(new Coordonate(fiveone, 5, 1));
        coordonates.add(new Coordonate(fivetwo, 5, 2));
        coordonates.add(new Coordonate(fivethree, 5, 3));
        coordonates.add(new Coordonate(fivefour, 5, 4));
        coordonates.add(new Coordonate(fivefive, 5, 5));
        coordonates.add(new Coordonate(fivesix, 5, 6));
        coordonates.add(new Coordonate(fiveseven, 5, 7));

        coordonates.add(new Coordonate(sixzero, 6, 0));
        coordonates.add(new Coordonate(sixone, 6, 1));
        coordonates.add(new Coordonate(sixtwo, 6, 2));
        coordonates.add(new Coordonate(sixthree, 6, 3));
        coordonates.add(new Coordonate(sixfour, 6, 4));
        coordonates.add(new Coordonate(sixfive, 6, 5));
        coordonates.add(new Coordonate(sixsix, 6, 6));
        coordonates.add(new Coordonate(sixseven, 6, 7));

        coordonates.add(new Coordonate(sevenzero, 7, 0));
        coordonates.add(new Coordonate(sevenone, 7, 1));
        coordonates.add(new Coordonate(seventwo, 7, 2));
        coordonates.add(new Coordonate(seventhree, 7, 3));
        coordonates.add(new Coordonate(sevenfour, 7, 4));
        coordonates.add(new Coordonate(sevenfive, 7, 5));
        coordonates.add(new Coordonate(sevensix, 7, 6));
        coordonates.add(new Coordonate(sevenseven, 7, 7));
    }

    public Coordonate getCoordonatefromLocation(int x, int y) {
        for (Coordonate coordonate : coordonates) {
            if (coordonate.X == x && coordonate.Y == y) {
                return coordonate;
            }
        }
        return null;
    }

    public void doCheckMateAction(){
        MainPanel.setBackground(Color.green);
        ButtonPane.setRightComponent(TextPanel);
        if (Game.getInstance().currentColor == Colors.WHITE) {
            TextPanel.setLayout(new FlowLayout());
            TextPanel.setBackground(Color.BLACK);
            JLabel jLabel = new JLabel("Black Team won!", SwingConstants.CENTER);
            jLabel.setForeground(Color.WHITE);
            TextPanel.add(jLabel);
        }else{
            TextPanel.setLayout(new GridLayout());
            TextPanel.add(new JLabel("White Team won!", SwingConstants.CENTER), CENTER_ALIGNMENT);
            TextPanel.setBackground(Color.WHITE);
        }
    }
}

class Coordonate extends JPanel {
    int X;
    int Y;
    public JPanel jPanel;
    public MouseListenerOnOff action;

    public Coordonate(JPanel jPanel, int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.jPanel = jPanel;
    }

    @Override
    public String toString() {
        return "Coordonate{" +
                "X=" + X +
                ", Y=" + Y + "}";
    }

    public void addAction(MouseListenerOnOff mouseListenerOnOff){
        action = mouseListenerOnOff;
    }
}

abstract class MouseListenerOnOff extends MouseAdapter {
    private boolean active;

    public abstract void mousePressed(MouseEvent e);

    public MouseListenerOnOff() {
        super();
        active = true;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }
}



