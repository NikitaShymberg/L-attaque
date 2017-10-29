import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by 39441 on 7/6/14.
 */
public class Piece extends ImageView {
    private int value; //This determines which piece will win when the fight method is called
    private Tile t; //The tile that the piece is currently on
    private boolean visible; //Determines which image the tile should get when the setVisible() method is called
    private Player owner; //The owner of the tile
    private boolean fought; //Set to true when the piece fights on its turn
    private boolean defended; //Set to true when the piece fights on its opponets turn

    public Piece(int vvalue, Player player) {

        value = vvalue;
        owner = player;
        t = null;
        visible = true;

        //The image for a piece is determined by its colour and value
        if (owner.getNum() == 1) {
            if (value == 0)
                setImage(new Image("images/Blue/BFlag.png"));
            else if (value == 1)
                setImage(new Image("images/Blue/BOne.png"));
            else if (value == 2)
                setImage(new Image("images/Blue/BTwo.png"));
            else if (value == 3)
                setImage(new Image("images/Blue/BThree.png"));
            else if (value == 4)
                setImage(new Image("images/Blue/BFour.png"));
            else if (value == 5)
                setImage(new Image("images/Blue/BFive.png"));
            else if (value == 6)
                setImage(new Image("images/Blue/BSix.png"));
            else if (value == 7)
                setImage(new Image("images/Blue/BSeven.png"));
            else if (value == 8)
                setImage(new Image("images/Blue/BEight.png"));
            else if (value == 9)
                setImage(new Image("images/Blue/BNine.png"));
            else if (value == 10)
                setImage(new Image("images/Blue/BTen.png"));
            else if (value == 11)
                setImage(new Image("images/Blue/BBomb.png"));
        } else {
            if (value == 0)
                setImage(new Image("images/Red/RFlag.png"));
            else if (value == 1)
                setImage(new Image("images/Red/ROne.png"));
            else if (value == 2)
                setImage(new Image("images/Red/RTwo.png"));
            else if (value == 3)
                setImage(new Image("images/Red/RThree.png"));
            else if (value == 4)
                setImage(new Image("images/Red/RFour.png"));
            else if (value == 5)
                setImage(new Image("images/Red/RFive.png"));
            else if (value == 6)
                setImage(new Image("images/Red/RSix.png"));
            else if (value == 7)
                setImage(new Image("images/Red/RSeven.png"));
            else if (value == 8)
                setImage(new Image("images/Red/REight.png"));
            else if (value == 9)
                setImage(new Image("images/Red/RNine.png"));
            else if (value == 10)
                setImage(new Image("images/Red/RTen.png"));
            else if (value == 11)
                setImage(new Image("images/Red/RBomb.png"));
        }
        this.setPreserveRatio(true);
        this.setFitHeight(40);
    }


    public Player getOwner() {
        return owner;
    }

    public int getValue() {
        return value;
    }

    public Tile getTile() {
        return t;
    }

    public void setTile(Tile tt) {
        t = tt;
        tt.setPiece(this);
    }

    public void setVisible() {
        if (visible)
            setImage(new Image("images/Hidden.png"));
        else {
            if (!(Main.switched) && this.getOwner().getNum() == 1 || Main.switched && this.getOwner().getNum() == 2) {
                if (value == 0)
                    setImage(new Image("images/Blue/BFlag.png"));
                else if (value == 1)
                    setImage(new Image("images/Blue/BOne.png"));
                else if (value == 2)
                    setImage(new Image("images/Blue/BTwo.png"));
                else if (value == 3)
                    setImage(new Image("images/Blue/BThree.png"));
                else if (value == 4)
                    setImage(new Image("images/Blue/BFour.png"));
                else if (value == 5)
                    setImage(new Image("images/Blue/BFive.png"));
                else if (value == 6)
                    setImage(new Image("images/Blue/BSix.png"));
                else if (value == 7)
                    setImage(new Image("images/Blue/BSeven.png"));
                else if (value == 8)
                    setImage(new Image("images/Blue/BEight.png"));
                else if (value == 9)
                    setImage(new Image("images/Blue/BNine.png"));
                else if (value == 10)
                    setImage(new Image("images/Blue/BTen.png"));
                else if (value == 11)
                    setImage(new Image("images/Blue/BBomb.png"));
            } else {
                if (value == 0)
                    setImage(new Image("images/Red/RFlag.png"));
                else if (value == 1)
                    setImage(new Image("images/Red/ROne.png"));
                else if (value == 2)
                    setImage(new Image("images/Red/RTwo.png"));
                else if (value == 3)
                    setImage(new Image("images/Red/RThree.png"));
                else if (value == 4)
                    setImage(new Image("images/Red/RFour.png"));
                else if (value == 5)
                    setImage(new Image("images/Red/RFive.png"));
                else if (value == 6)
                    setImage(new Image("images/Red/RSix.png"));
                else if (value == 7)
                    setImage(new Image("images/Red/RSeven.png"));
                else if (value == 8)
                    setImage(new Image("images/Red/REight.png"));
                else if (value == 9)
                    setImage(new Image("images/Red/RNine.png"));
                else if (value == 10)
                    setImage(new Image("images/Red/RTen.png"));
                else if (value == 11)
                    setImage(new Image("images/Red/RBomb.png"));
            }

        }
        visible = !visible;
    }

    public void kill() {
        if (t != null) {
            t.removePiece();
            t.setPiece(null);
            t = null;
        }
        this.getOwner().addHand(this);
    }

    //Fight method to be called if piece a steps onto piece b
    //Must move piece a to where it was going to be moved initially if it wins
    public Piece fight(Piece b) {
        if (this instanceof Mine || b instanceof Mine) {
            if (this.getValue() == 3) {
                fought(true);
                this.setVisible();
                return this;
            } else if (b.getValue() == 3) {
                b.defended(true);
                b.setVisible();
                return b;
            } else return null;
        } else if (this.getValue() == 10 && b instanceof Spy) {
            b.defended(true);
            b.setVisible();
            return b;
        } else if (this instanceof Spy && b.getValue() == 10) {
            fought(true);
            setVisible();
            return this;
        } else if (this.getValue() > b.getValue()) {
            fought(true);
            setVisible();
            return this;
        } else if (b.getValue() > this.getValue()) {
            b.defended(true);
            b.setVisible();
            return b;
        } else return null;
    }

    public boolean isFought() {
        return fought;
    }

    public void fought(boolean ffought) {
        fought = ffought;
    }

    public boolean isDefended(){
        return defended;
    }

    public void defended(boolean defended) {
        this.defended = defended;
    }
}

