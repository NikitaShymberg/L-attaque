/**
 * Created by 39441 on 7/7/14.
 */
public class PieceMove extends Piece {

    public PieceMove(int vvalue, Player pplayer) {
        super(vvalue, pplayer);
    }

    public void moveUp(){
        if (super.getTile().getTop().getPiece() == null) { //If the tile above the piece is empty it moves normally
            super.setTile(super.getTile().getTop());
            super.getTile().getBot().setPiece(null);
            super.getTile().setPiece(this);
        } else if(super.getTile().getTop().getPiece().getOwner() != this.getOwner()) { //If the tile above the piece has an enemy piece they must fight
            if(this.fight(super.getTile().getTop().getPiece()) == null) { //The pieces have equal values so both die
                super.getTile().getTop().getPiece().kill();
                this.kill();
            } else if(this.fight(super.getTile().getTop().getPiece()).getOwner().getNum() == this.getOwner().getNum()) { //This piece is the winner so the enemy dies and this one moves up
                super.getTile().getTop().getPiece().kill();
                this.moveUp();
            } else {
                this.kill(); //This piece loses the fight and so is removed
            }
        } else
            return;
    }

    public void moveLeft(){
        if (super.getTile().getLeft().getPiece() == null) {
            super.setTile(super.getTile().getLeft());
            super.getTile().getRight().setPiece(null);
            super.getTile().setPiece(this);
        } else if(super.getTile().getLeft().getPiece().getOwner() != this.getOwner()) {
            if(this.fight(super.getTile().getLeft().getPiece()) == null) {
                super.getTile().getLeft().getPiece().kill();
                this.kill();
            } else if(this.fight(super.getTile().getLeft().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                super.getTile().getLeft().getPiece().kill();
                this.moveLeft();
            } else {
                this.kill();
            }
        } else
            return;
    }

    public void moveRight(){
        if (super.getTile().getRight().getPiece() == null) {
            super.setTile(super.getTile().getRight());
            super.getTile().getLeft().setPiece(null);
            super.getTile().setPiece(this);
        } else if(super.getTile().getRight().getPiece().getOwner() != this.getOwner()) {
            if(this.fight(super.getTile().getRight().getPiece()) == null) {
                super.getTile().getRight().getPiece().kill();
                this.kill();
            } else if(this.fight(super.getTile().getRight().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                super.getTile().getRight().getPiece().kill();
                this.moveRight();
            } else {
                this.kill();
            }
        } else
            return;
    }

    public void moveDown() {
        if (super.getTile().getBot().getPiece() == null) {
            super.setTile(super.getTile().getBot());
            super.getTile().getTop().setPiece(null);
            super.getTile().setPiece(this);
        } else if (super.getTile().getBot().getPiece().getOwner() != this.getOwner()) {
            if (this.fight(super.getTile().getBot().getPiece()) == null) {
                super.getTile().getBot().getPiece().kill();
                this.kill();
            } else if (this.fight(super.getTile().getBot().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                super.getTile().getBot().getPiece().kill();
                this.moveDown();
            } else {
                this.kill();
            }
        } else
            return;
    }
}
