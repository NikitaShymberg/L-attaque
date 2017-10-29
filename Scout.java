/**
 * Created by 39441 on 7/9/14.
 */
public class Scout extends PieceMove {

    public Scout(Player pplayer) {
        super(2, pplayer);
    }

    //The scout moves d amount of squares
    public void moveUp(int d){
        Tile t = super.getTile();

        for (int i = 0; i < d - 1; i++) {
            t = t.getTop();
        }
        if(t.getTop().getPiece() != null) {
            if (t.getTop().getPiece().getOwner() != this.getOwner()) {
                if (this.fight(t.getTop().getPiece()) == null) {
                    t.getTop().getPiece().kill();
                    this.kill();
                } else if (this.fight(t.getTop().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                    t.getTop().getPiece().kill();
                } else {
                    this.kill();
                }
            } else
                return;
        }
        if(super.getTile() != null) {
            super.getTile().setPiece(null);
            super.setTile(t.getTop());
        }

    }

    public void moveLeft(int d){
        Tile t = super.getTile();

        for (int i = 0; i < d - 1; i++) {
            t = t.getLeft();
        }
        if(t.getLeft().getPiece() != null) {
            if (t.getLeft().getPiece().getOwner() != this.getOwner()) {
                if (this.fight(t.getLeft().getPiece()) == null) {
                    t.getLeft().getPiece().kill();
                    this.kill();
                } else if (this.fight(t.getLeft().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                    t.getLeft().getPiece().kill();
                } else {
                    this.kill();
                }
            } else
                return;
        }
        if(super.getTile() != null) {
            super.getTile().setPiece(null);
            super.setTile(t.getLeft());
        }

    }

    public void moveRight(int d){
        Tile t = super.getTile();

        for (int i = 0; i < d - 1; i++) {
            t = t.getRight();
        }
        if(t.getRight().getPiece() != null) {
            if (t.getRight().getPiece().getOwner() != this.getOwner()) {
                if (this.fight(t.getRight().getPiece()) == null) {
                    t.getRight().getPiece().kill();
                    this.kill();
                } else if (this.fight(t.getRight().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                    t.getRight().getPiece().kill();
                } else {
                    this.kill();
                }
            } else
                return;
        }
        if(super.getTile() != null) {
            super.getTile().setPiece(null);
            super.setTile(t.getRight());
        }

    }

    public void moveDown(int d){
        Tile t = super.getTile();

        for (int i = 0; i < d - 1; i++) {
            t = t.getBot();
        }
        if(t.getBot().getPiece() != null) {
            if (t.getBot().getPiece().getOwner() != this.getOwner()) {
                if (this.fight(t.getBot().getPiece()) == null) {
                    t.getBot().getPiece().kill();
                    this.kill();
                } else if (this.fight(t.getBot().getPiece()).getOwner().getNum() == this.getOwner().getNum()) {
                    t.getBot().getPiece().kill();
                } else {
                    this.kill();
                }
            } else
                return;
        }
        if(super.getTile() != null) {
            super.getTile().setPiece(null);
            super.setTile(t.getBot());
        }

    }
}
