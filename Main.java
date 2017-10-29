/*
 ============================================================================
 Name: Nikita Shymberg
 Date: 2014/01/09
 Description: This is the board game 'L'attaque'
 ============================================================================
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int PLAYER1_PLACEMENT = 1;
    private static final int PLAYER2_PLACEMENT = 2;
    private static final int GAME_ON = 3;
    private static final int PIECE_TYPES = 12; //The amount of types of pieces
    private BorderPane root;
    private Stage stage;
    private Scene scene;
    private Board board;
    private int gamePhase; //current game phase
    private int currentPlacementIndex;
    private StackPane stackPane;
    private AnchorPane anchorPane;
    public Player one, two;
    private Tile active;
    private Tile test;
    private HBox topBox;
    private HBox botBox;
    private ImageView[] deadTopViews;
    private Label[] deadTopCountLabel;
    private ImageView[] deadBotViews;
    private Label[] deadBotCountLabel;
    public static boolean switched; //Used to know if player one and two stayed the same or were switched

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        scene = new Scene(root, 660, 700); //width and height of application
        stage.setScene(scene);
        stage.setTitle("L'attaque");  //text for the title bar of the window

        scene.getStylesheets().add("styles.css");

        stage.setResizable(false);

        //Setup part 1: Choose a player to go first
        two = new Player(false, 2);
        one = new Player(false, 1);


        if (Math.random() * 2 > 1) {
            one.setTurn();
        } else  {
            two.setTurn();
            Player temp = new Player(false, 1);
            temp = one;
            one = two;
            two = temp;
            one.setNum(1);
            two.setNum(2);
            switched = true;
        }

        //Setup part 2: Create a board
        board = new Board(scene);

        anchorPane = new AnchorPane();
        stackPane = new StackPane(board, anchorPane);
        topBox = new HBox();
        botBox = new HBox();


        root.setCenter(stackPane);
        root.setTop(topBox);
        root.setBottom(botBox);

        deadTopViews = new ImageView[PIECE_TYPES];
        deadTopCountLabel = new Label[PIECE_TYPES];

        //Setup part 3: Images for dead pieces
        for (int i = 0; i < PIECE_TYPES; i++) {
            deadTopViews[i] = new ImageView();
            deadTopViews[i].setFitWidth(50);
            deadTopViews[i].setPreserveRatio(true);
            deadTopCountLabel[i] = new Label("0");
            topBox.getChildren().add( new VBox(deadTopViews[i], new HBox(deadTopCountLabel[i])));
        }

        if(!switched) {
            deadTopViews[0].setImage(new Image("images/Blue/BFlag.png"));
            deadTopViews[1].setImage(new Image("images/Blue/BOne.png"));
            deadTopViews[2].setImage(new Image("images/Blue/BTwo.png"));
            deadTopViews[3].setImage(new Image("images/Blue/BThree.png"));
            deadTopViews[4].setImage(new Image("images/Blue/BFour.png"));
            deadTopViews[5].setImage(new Image("images/Blue/BFive.png"));
            deadTopViews[6].setImage(new Image("images/Blue/BSix.png"));
            deadTopViews[7].setImage(new Image("images/Blue/BSeven.png"));
            deadTopViews[8].setImage(new Image("images/Blue/BEight.png"));
            deadTopViews[9].setImage(new Image("images/Blue/BNine.png"));
            deadTopViews[10].setImage(new Image("images/Blue/BTen.png"));
            deadTopViews[11].setImage(new Image("images/Blue/BBomb.png"));
        } else {
            deadTopViews[0].setImage(new Image("images/Red/RFlag.png"));
            deadTopViews[1].setImage(new Image("images/Red/ROne.png"));
            deadTopViews[2].setImage(new Image("images/Red/RTwo.png"));
            deadTopViews[3].setImage(new Image("images/Red/RThree.png"));
            deadTopViews[4].setImage(new Image("images/Red/RFour.png"));
            deadTopViews[5].setImage(new Image("images/Red/RFive.png"));
            deadTopViews[6].setImage(new Image("images/Red/RSix.png"));
            deadTopViews[7].setImage(new Image("images/Red/RSeven.png"));
            deadTopViews[8].setImage(new Image("images/Red/REight.png"));
            deadTopViews[9].setImage(new Image("images/Red/RNine.png"));
            deadTopViews[10].setImage(new Image("images/Red/RTen.png"));
            deadTopViews[11].setImage(new Image("images/Red/RBomb.png"));
        }

        deadBotViews = new ImageView[PIECE_TYPES];
        deadBotCountLabel = new Label[PIECE_TYPES];

        for (int i = 0; i < PIECE_TYPES; i++) {
            deadBotViews[i] = new ImageView();
            deadBotViews[i].setFitWidth(50);
            deadBotViews[i].setPreserveRatio(true);
            deadBotCountLabel[i] = new Label("0");
            botBox.getChildren().add( new VBox(deadBotViews[i], new HBox(deadBotCountLabel[i])));
        }

        if(switched) {
            deadBotViews[0].setImage(new Image("images/Blue/BFlag.png"));
            deadBotViews[1].setImage(new Image("images/Blue/BOne.png"));
            deadBotViews[2].setImage(new Image("images/Blue/BTwo.png"));
            deadBotViews[3].setImage(new Image("images/Blue/BThree.png"));
            deadBotViews[4].setImage(new Image("images/Blue/BFour.png"));
            deadBotViews[5].setImage(new Image("images/Blue/BFive.png"));
            deadBotViews[6].setImage(new Image("images/Blue/BSix.png"));
            deadBotViews[7].setImage(new Image("images/Blue/BSeven.png"));
            deadBotViews[8].setImage(new Image("images/Blue/BEight.png"));
            deadBotViews[9].setImage(new Image("images/Blue/BNine.png"));
            deadBotViews[10].setImage(new Image("images/Blue/BTen.png"));
            deadBotViews[11].setImage(new Image("images/Blue/BBomb.png"));
        } else {
            deadBotViews[0].setImage(new Image("images/Red/RFlag.png"));
            deadBotViews[1].setImage(new Image("images/Red/ROne.png"));
            deadBotViews[2].setImage(new Image("images/Red/RTwo.png"));
            deadBotViews[3].setImage(new Image("images/Red/RThree.png"));
            deadBotViews[4].setImage(new Image("images/Red/RFour.png"));
            deadBotViews[5].setImage(new Image("images/Red/RFive.png"));
            deadBotViews[6].setImage(new Image("images/Red/RSix.png"));
            deadBotViews[7].setImage(new Image("images/Red/RSeven.png"));
            deadBotViews[8].setImage(new Image("images/Red/REight.png"));
            deadBotViews[9].setImage(new Image("images/Red/RNine.png"));
            deadBotViews[10].setImage(new Image("images/Red/RTen.png"));
            deadBotViews[11].setImage(new Image("images/Red/RBomb.png"));
        }

        gamePhase = PLAYER1_PLACEMENT;

        placePlayers();

        stage.show();

    }

    //Setup part 4: Players place pieces
    private void placePlayers() {
        currentPlacementIndex = 0;

        placeNextPiece();
    }

    private void placeNextPiece() {
        placeNextPiece(0, 0);
    }

    private void placeNextPiece(double x, double y) {


        //first player places pieces
        if (currentPlacementIndex == one.getHand().length && gamePhase == PLAYER1_PLACEMENT) {
            for (int i = 0; i < board.ROWS; i++) {
                for (int j = 0; j < board.COLS; j++) {
                    if(board.getTile(i,j).getPiece() != null) {
                        board.getTile(i, j).getPiece().setVisible();
                    }
                }

            }
            currentPlacementIndex = 0;
            one.clearHand();
            gamePhase = PLAYER2_PLACEMENT;

        }
        //second player plaes pieces
        if (currentPlacementIndex == two.getHand().length && gamePhase == PLAYER2_PLACEMENT) {
            for (int i = 0; i < board.ROWS; i++) {
                for (int j = 0; j < board.COLS; j++) {
                    if(board.getTile(i,j).getPiece() != null)
                        board.getTile(i,j).getPiece().setVisible();
                }

            }
            gamePhase = GAME_ON;
            two.clearHand();
            anchorPane.setMouseTransparent(true);
            for (int i = 0; i < board.ROWS; i++) {
                for (int j = 0; j < board.COLS; j++) {
                    if(board.getTile(i,j).getPiece() != null)
                        board.getTile(i,j).getPiece().setVisible();
                }

            }
            move();
            return;
        }

        Piece piece;


        if (gamePhase == PLAYER1_PLACEMENT)
            piece = one.getHand()[currentPlacementIndex];
        else
            piece = two.getHand()[currentPlacementIndex];

        anchorPane.getChildren().add(piece);
        piece.setX(x - piece.getLayoutBounds().getWidth() / 2);
        piece.setY(y - piece.getLayoutBounds().getHeight() / 2);


        anchorPane.setOnMouseMoved(event -> {
            piece.setX(event.getX() - piece.getLayoutBounds().getWidth() / 2 - 5);
            piece.setY(event.getY() - piece.getLayoutBounds().getHeight() / 2 - 5);
        });
        //figure out which tile the piece is placed on
        piece.setOnMouseClicked(event -> {
            if (gamePhase == PLAYER1_PLACEMENT) {
                for (int i = 0; i < board.ROWS; i++) {
                    for (int j = 0; j < board.COLS; j++) {
                        if (board.getTile(i, j).getLayoutX() < event.getX() && board.getTile(i, j).getLayoutX() + board.getTile(i, j).getWidth() > event.getX()
                                && board.getTile(i, j).getLayoutY() < event.getY() && board.getTile(i, j).getLayoutY() + board.getTile(i, j).getHeight() > event.getY()
                                ) {
                            if (i > 5) {

                                anchorPane.getChildren().remove(piece);
                                if (board.getTile(i, j).setPiece(piece)) {
                                    piece.setTile(board.getTile(i, j));

                                    if (currentPlacementIndex < one.getHand().length) {
                                        currentPlacementIndex++;

                                        placeNextPiece(event.getX(), event.getY());
                                    }
                                } else {
                                    anchorPane.getChildren().add(piece);
                                }
                            }

                        }
                    }
                }

            } else  if (gamePhase == PLAYER2_PLACEMENT) {
                for (int i = 0; i < board.ROWS; i++) {
                    for (int j = 0; j < board.COLS; j++) {
                        if (board.getTile(i, j).getLayoutX() < event.getX() && board.getTile(i, j).getLayoutX() + board.getTile(i, j).getWidth() > event.getX()
                                && board.getTile(i, j).getLayoutY() < event.getY() && board.getTile(i, j).getLayoutY() + board.getTile(i, j).getHeight() > event.getY()
                                ) {
                            if (i < 4) {

                                anchorPane.getChildren().remove(piece);
                                if (board.getTile(i, j).setPiece(piece)) {
                                    piece.setTile(board.getTile(i, j));

                                    if (currentPlacementIndex < one.getHand().length) {
                                        currentPlacementIndex++;

                                        placeNextPiece(event.getX(), event.getY());
                                    }
                                } else {
                                    anchorPane.getChildren().add(piece);
                                }
                            }

                        }
                    }
                }

            }


        });
    }
    //Game starts
    public void move() {
        //clear all listening tiles
        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                board.getTile(i,j).setOnMouseClicked(event -> {});
            }

        }
        if(gamePhase == GAME_ON) {
            p1Move();
        }
    }
    //Player one moves part 1: selecting active tile and preparation
    public void p1Move() {
        //check if player 1 has lost the gmae
        for (int i = 0; i < one.getHandCount(); i++) {
            if (one.getHand()[i] instanceof Flag)
                P2Victory();
        }


        for (int i = 0; i < PIECE_TYPES; i++) {
            deadTopCountLabel[i].setText("0");
        }

        for (int i = 0; i < PIECE_TYPES; i++) {
            deadBotCountLabel[i].setText("0");
        }
        //show numbers of dead pieces for each player
        for (int i = 0; i < one.getHand().length; i++) {
            if(one.getHand()[i] != null && switched)
                deadTopCountLabel[one.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadTopCountLabel[one.getHand()[i].getValue()].getText())+1));
            else if(one.getHand()[i] != null)
                deadBotCountLabel[one.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadBotCountLabel[one.getHand()[i].getValue()].getText())+1));
        }

        for (int i = 0; i < two.getHand().length; i++) {
            if(two.getHand()[i] != null && switched)
                deadBotCountLabel[two.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadBotCountLabel[two.getHand()[i].getValue()].getText())+1));
            else if(two.getHand()[i] != null)
                deadTopCountLabel[two.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadTopCountLabel[two.getHand()[i].getValue()].getText())+1));
        }

        //show any opponents pieces that have fought last turn
        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                if(board.getTile(i,j).getPiece() != null && !board.getTile(i,j).getPiece().isFought())
                    board.getTile(i,j).getPiece().setVisible();
                board.getTile(i,j).deactivate();
                if(board.getTile(i,j).getPiece() != null  && board.getTile(i,j).getPiece().getOwner().getNum() == 1)
                    board.getTile(i,j).getPiece().fought(false);
                if(board.getTile(i,j).getPiece() != null && (board.getTile(i,j).getPiece().isDefended() && board.getTile(i,j).getPiece().getOwner().getNum() == 1)){
                    board.getTile(i,j).getPiece().fought(true);
                    board.getTile(i,j).getPiece().defended(false);
                }
            }

        }
        //clear all active tiles
        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                board.getTile(i,j).setOnMouseClicked(event -> {});
            }

        }
        //activate all pieces that can move for player
        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                Tile tile = board.getTile(i, j);

                if (tile.getPiece() != null && tile.getPiece().getOwner().getNum() == 1 && tile.getPiece() instanceof PieceMove) {
                    tile.setOnMouseClicked(event -> {
                        for (int k = 0; k < board.ROWS; k++) {
                            for (int l = 0; l < board.COLS; l++) {
                                if(board.getTile(k,l) != active) {
                                    board.getTile(k, l).deactivate();
                                }
                            }
                        }
                        tile.setActive(true);
                        if (active != null)
                            active.setActive(false);
                        active = tile;
                        p1Move2();
                    });
                }

            }

        }
    }
    //Player one moves part 2: moving the piece
    public void p1Move2() {

        if (active != null && !(active.getPiece() instanceof Scout)) {
            //Moving up
            if (active.getTop() != null && (active.getTop().isPass() && (active.getTop().getPiece() == null || active.getTop().getPiece() != null && active.getTop().getPiece().getOwner().getNum() != 1))) {
                active.getTop().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                active.getTop().setOnMouseClicked(event -> {
                    ((PieceMove) active.getPiece()).moveUp();

                    p2Move();
                });
            }
            //Moving left
            if (active.getLeft() != null && (active.getLeft().isPass() && (active.getLeft().getPiece() == null || active.getLeft().getPiece() != null && active.getLeft().getPiece().getOwner().getNum() != 1))) {
                active.getLeft().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                active.getLeft().setOnMouseClicked(event -> {
                    ((PieceMove) active.getPiece()).moveLeft();

                    p2Move();
                });
            }
            //Moving right
            if (active.getRight() != null && (active.getRight().isPass() && (active.getRight().getPiece() == null || active.getRight().getPiece() != null && active.getRight().getPiece().getOwner().getNum() != 1))) {
                    active.getRight().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                    active.getRight().setOnMouseClicked(event -> {
                        ((PieceMove) active.getPiece()).moveRight();

                        p2Move();
                    });
                }
            //Moving down
                if (active.getBot() != null && (active.getBot().isPass() && (active.getBot().getPiece() == null || active.getBot().getPiece() != null && active.getBot().getPiece().getOwner().getNum() != 1))) {
                    active.getBot().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                    active.getBot().setOnMouseClicked(event -> {
                        ((PieceMove) active.getPiece()).moveDown();

                        p2Move();
                    });
                }
            } else if(active != null && active.getPiece() instanceof Scout) {
            p1MoveScout();
        }
    }
    //Player one moves part 3: moving scouts
    public void p1MoveScout(){

        test = active;
        int count = 0;

        //Check top
        while (test.getTop() != null && test.getTop().getPiece() == null && test.getTop().isPass()) {
                test = test.getTop();
                count ++;
            }

        if(test.getTop() != null && test.getTop().getPiece() != null && test.getTop().getPiece().getOwner().getNum() == 2)
            count ++;

        test = active;

        //Move up
        for (int i = 0; i < count; i++) {
            test.getTop().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
            test.getTop().setOnMouseClicked(event -> {
                if(active.getPiece() instanceof Scout) {
                    Tile me = (Tile) event.getSource();
                    ((Scout) active.getPiece()).moveUp(active.getRow() - me.getRow());

                    p2Move();
                }
            });
            test = test.getTop();
        }

        test = active;
        count = 0;

        //Check right
        while (test.getRight() != null && test.getRight().getPiece() == null && test.getRight().isPass()) {
                test = test.getRight();
                count ++;
            }

        if(test.getRight() != null && test.getRight().getPiece() != null && test.getRight().getPiece().getOwner().getNum() == 2)
            count ++;

        test = active;

        //Move right
        for (int i = 0; i < count; i++) {
            if(test != null && test.getRight() != null) {
                test.getRight().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
                test.getRight().setOnMouseClicked(event -> {
                    if(active.getPiece() instanceof Scout) {
                        Tile me = (Tile) event.getSource();
                        ((Scout) active.getPiece()).moveRight(me.getCol() - active.getCol());

                        p2Move();
                    }
                });
                test = test.getRight();
            }
        }

        test = active;
        count = 0;

        //Check Left
        while (test.getLeft() != null && test.getLeft().getPiece() == null && test.getLeft().isPass()) {
                test = test.getLeft();
                count ++;
            }

        if(test.getLeft() != null && test.getLeft().getPiece() != null && test.getLeft().getPiece().getOwner().getNum() == 2)
            count ++;

        test = active;

        //Move left
        for (int i = 0; i < count; i++) {
            if(test != null && test.getLeft() != null) {
                test.getLeft().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
                test.getLeft().setOnMouseClicked(event -> {
                    if(active.getPiece() instanceof Scout) {
                        Tile me = (Tile) event.getSource();
                        ((Scout) active.getPiece()).moveLeft(active.getCol() - me.getCol());

                        p2Move();
                    }
                });
                test = test.getLeft();
            }
        }

        test = active;
        count = 0;
        //Check Bot
            while (test.getBot() != null && test.getBot().getPiece() == null && test.getBot().isPass()) {
                test = test.getBot();
                count ++;
            }

        if(test.getBot() != null && test.getBot().getPiece() != null && test.getBot().getPiece().getOwner().getNum() == 2)
            count ++;

        test = active;

        //Move down
        for (int i = 0; i < count; i++) {
            if(test != null && test.getBot() != null) {
                test.getBot().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
                test.getBot().setOnMouseClicked(event -> {
                    if(active.getPiece() instanceof Scout) {
                        Tile me = (Tile) event.getSource();
                        ((Scout) active.getPiece()).moveDown(me.getRow() - active.getRow());

                        p2Move();
                    }
                });
                test = test.getBot();
            }
        }

    }

    //Player two move part one: selecting active tile and preparation
    public void p2Move(){

        for (int i = 0; i < two.getHand().length; i++) {
            if (two.getHand()[i] instanceof Flag)
                P1Victory();
        }

        for (int i = 0; i < PIECE_TYPES; i++) {
            deadTopCountLabel[i].setText("0");
        }

        for (int i = 0; i < PIECE_TYPES; i++) {
            deadBotCountLabel[i].setText("0");
        }

        for (int i = 0; i < one.getHand().length; i++) {
            if(one.getHand()[i] != null && switched)
                deadTopCountLabel[one.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadTopCountLabel[one.getHand()[i].getValue()].getText())+1));
            else if(one.getHand()[i] != null)
                deadBotCountLabel[one.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadBotCountLabel[one.getHand()[i].getValue()].getText())+1));
        }

        for (int i = 0; i < two.getHand().length; i++) {
            if(two.getHand()[i] != null && switched)
                deadBotCountLabel[two.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadBotCountLabel[two.getHand()[i].getValue()].getText())+1));
            else if(two.getHand()[i] != null)
                deadTopCountLabel[two.getHand()[i].getValue()].setText("" + (Integer.parseInt(deadTopCountLabel[two.getHand()[i].getValue()].getText())+1));
        }

        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                if(board.getTile(i,j).getPiece() != null && !board.getTile(i,j).getPiece().isFought())
                    board.getTile(i,j).getPiece().setVisible();
                board.getTile(i,j).deactivate();
                if(board.getTile(i,j).getPiece() != null && board.getTile(i,j).getPiece().getOwner().getNum() == 2)
                    board.getTile(i,j).getPiece().fought(false);
                if(board.getTile(i,j).getPiece() != null && (board.getTile(i,j).getPiece().isDefended() && board.getTile(i,j).getPiece().getOwner().getNum() == 2)){
                    board.getTile(i,j).getPiece().fought(true);
                    board.getTile(i,j).getPiece().defended(false);
                }

            }

        }

        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                board.getTile(i,j).setOnMouseClicked(null);
            }

        }
        for (int i = 0; i < board.ROWS; i++) {
            for (int j = 0; j < board.COLS; j++) {
                Tile tile = board.getTile(i, j);

                if (tile.getPiece() != null && tile.getPiece().getOwner().getNum() == 2 && tile.getPiece() instanceof PieceMove) {
                    tile.setOnMouseClicked(event -> {
                        for (int k = 0; k < board.ROWS; k++) {
                            for (int l = 0; l < board.COLS; l++) {
                                if(board.getTile(k,l) != active) {
                                    board.getTile(k, l).deactivate();
                                }
                            }
                        }
                        tile.setActive(true);
                        if (active != null)
                            active.setActive(false);
                        active = tile;
                        p2Move2();
                    });
                }

            }

        }

    }
    //Player two moves part two: moving the piece
    public void p2Move2(){
        if (active != null && !(active.getPiece() instanceof Scout)) {


            if (active.getTop() != null && (active.getTop().isPass() && (active.getTop().getPiece() == null || active.getTop().getPiece() != null && active.getTop().getPiece().getOwner().getNum() != 2))) {
                active.getTop().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                active.getTop().setOnMouseClicked(event -> {
                    ((PieceMove) active.getPiece()).moveUp();

                    move();
                });
            }
            if (active.getLeft() != null && (active.getLeft().isPass() && (active.getLeft().getPiece() == null || active.getLeft().getPiece() != null && active.getLeft().getPiece().getOwner().getNum() != 2))) {
                active.getLeft().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                active.getLeft().setOnMouseClicked(event -> {
                    ((PieceMove) active.getPiece()).moveLeft();

                    move();
                });
            }
            if (active.getRight() != null && (active.getRight().isPass() && (active.getRight().getPiece() == null || active.getRight().getPiece() != null && active.getRight().getPiece().getOwner().getNum() != 2))) {
                    active.getRight().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                    active.getRight().setOnMouseClicked(event -> {
                        ((PieceMove) active.getPiece()).moveRight();

                        move();
                    });
                }
                if (active.getBot() != null && (active.getBot().isPass() && (active.getBot().getPiece() == null || active.getBot().getPiece() != null && active.getBot().getPiece().getOwner().getNum() != 2))) {
                    active.getBot().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));

                    active.getBot().setOnMouseClicked(event -> {
                        ((PieceMove) active.getPiece()).moveDown();

                        move();
                    });
                }
            } else if(active != null && active.getPiece() instanceof Scout) {
            p2MoveScout();
        }
    }
    //Player two moves part three: moving scouts
    public void p2MoveScout(){

        test = active;
        int count = 0;

        //Check top
        while (test.getTop() != null && test.getTop().getPiece() == null && test.getTop().isPass()) {
            test = test.getTop();
            count ++;
        }

        if(test.getTop() != null && test.getTop().getPiece() != null && test.getTop().getPiece().getOwner().getNum() == 1)
            count ++;

        test = active;

        //Move up
        for (int i = 0; i < count; i++) {
            test.getTop().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
            test.getTop().setOnMouseClicked(event -> {
                Tile me = (Tile)event.getSource();
                ((Scout)active.getPiece()).moveUp(active.getRow() - me.getRow());

                move();
            });
            test = test.getTop();
        }

        test = active;
        count = 0;

        //Check right
        while (test.getRight() != null && test.getRight().getPiece() == null && test.getRight().isPass()) {
            test = test.getRight();
            count ++;
        }

        if(test.getRight() != null && test.getRight().getPiece() != null && test.getRight().getPiece().getOwner().getNum() == 1)
            count ++;

        test = active;

        //Move right
        for (int i = 0; i < count; i++) {
            if(test != null && test.getRight() != null) {
                test.getRight().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
                test.getRight().setOnMouseClicked(event -> {
                    Tile me = (Tile)event.getSource();
                    ((Scout) active.getPiece()).moveRight(me.getCol() - active.getCol());

                    active.deactivate();

                    move();
                });
                test = test.getRight();
            }
        }

        test = active;
        count = 0;

        //Check Left
        while (test.getLeft() != null && test.getLeft().getPiece() == null && test.getLeft().isPass()) {
            test = test.getLeft();
            count ++;
        }

        if(test.getLeft() != null && test.getLeft().getPiece() != null && test.getLeft().getPiece().getOwner().getNum() == 1)
            count ++;

        test = active;

        //Move right
        for (int i = 0; i < count; i++) {
            if(test != null && test.getLeft() != null) {
                test.getLeft().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
                test.getLeft().setOnMouseClicked(event -> {
                    Tile me = (Tile)event.getSource();
                    ((Scout) active.getPiece()).moveLeft(active.getCol() - me.getCol());

                    move();
                });
                test = test.getLeft();
            }
        }

        test = active;
        count = 0;

        //Check Bot
        while (test.getBot() != null && test.getBot().getPiece() == null && test.getBot().isPass()) {
            test = test.getBot();
            count ++;
        }

        if(test.getBot() != null && test.getBot().getPiece() != null && test.getBot().getPiece().getOwner().getNum() == 1)
            count ++;

        test = active;

        //Move right
        for (int i = 0; i < count; i++) {
            if(test != null && test.getBot() != null) {
                test.getBot().getChildren().add(new Rectangle(20,20, Color.DARKGREEN));
                test.getBot().setOnMouseClicked(event -> {
                    Tile me = (Tile)event.getSource();
                    ((Scout)active.getPiece()).moveDown(me.getRow() - active.getRow());

                    move();
                });
                test = test.getBot();
            }
        }

    }
    //TODO: Fix victory callouts for each player
    //TODO: create some faster, automated testing
    public void P1Victory(){
        System.out.println("Player one wins!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Player one wins!");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations player one, you win!");

        alert.showAndWait();

        Platform.exit();

    }

    public void P2Victory(){
        System.out.println("Player two wins!");

        System.out.println("Player two wins!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Player two wins!");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations player two, you win!");

        alert.showAndWait();

        Platform.exit();

    }


    public static void main(String[] args) {
        launch(args);
    }
}