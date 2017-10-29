import javafx.scene.layout.GridPane;

/**
 * Created by 39441 on 7/9/14.
 */
public class Player extends GridPane {

    private boolean turn; //Whether it is a players turn or not
    private Piece[] hand; //The pieces the player holds at the beginning of the game, or the dead pieces during the game
    private int number; //Whether this is player one or two
    private int handCount; //How many pieces there are in Hand

    public Player(boolean tturn, int num){
        setNum(num);
        turn = tturn;
        hand = new Piece[]{new Flag(this), //All players have exactly those pieces in their hand at the start of the game
                new PieceMove(10, this),
                new PieceMove(9, this),
                new PieceMove(8, this),
                new PieceMove(8, this),
                new PieceMove(7, this),
                new PieceMove(7, this),
                new PieceMove(6, this),
                new PieceMove(6, this),
                new PieceMove(6, this),
                new PieceMove(6, this),
                new PieceMove(5, this),
                new PieceMove(5, this),
                new PieceMove(5, this),
                new PieceMove(5, this),
                new PieceMove(4, this),
                new PieceMove(4, this),
                new PieceMove(4, this),
                new PieceMove(4, this),
                new PieceMove(3, this),
                new PieceMove(3, this),
                new PieceMove(3, this),
                new PieceMove(3, this),
                new Scout(this),
                new Scout(this),
                new Scout(this),
                new Scout(this),
                new Scout(this),
                new Scout(this),
                new Scout(this),
                new Scout(this),
                new Spy(this),
                new Mine(this),
                new Mine(this),
                new Mine(this),
                new Mine(this),

        };

    }


    public void setTurn() {
        turn = !turn;
    }

    public Piece[] getHand(){
        return hand;
    }

    public void addHand(Piece p){
        hand[handCount] = p;
        handCount++;
    }

    public void setNum(int n) {number = n;}

    public int getNum() {return number;}

    public void clearHand() {
        handCount = 0;
        for (int i = 0; i < hand.length; i++) {
            hand[i] = null;
        }
    }

    public int getHandCount() {
        return handCount;
    }
}
