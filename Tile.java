import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by 39441 on 7/6/14.
 */
public class Tile extends StackPane {
    private Piece p; //The piece on the tile
    private boolean pass; //Whether this tile is passable or not
    private ImageView tileView; //The image of the tile
    private int row; //The row of the tile on the board
    private int col; //The column of the tile on the board

    public Tile (boolean ppass, int r, int c) {
        pass = ppass;
        p = null;
        row = r;
        col = c;

        if(ppass)
            tileView = new ImageView("images/Pass.png");
        else
            tileView = new ImageView("images/Impass.png");

        tileView.setFitWidth(55);
        tileView.setPreserveRatio(true);

        setMouseTransparent(false);
        setPickOnBounds(true);

        this.getChildren().add(tileView);
    }

    private Board getBoard() {
        if (this.getParent() != null)
            return (Board)this.getParent();

        return null;
    }

    public Tile getTop() {
        return getBoard().getTile(row - 1, col);
    }

    public Tile getLeft() {
        return getBoard().getTile(row, col - 1);
    }

    public Tile getRight() {
        return getBoard().getTile(row, col + 1);
    }

    public Tile getBot() {
        return getBoard().getTile(row + 1, col);
    }

    public Piece getPiece() {
        return p;
    }

    public boolean setPiece(Piece t) {

        if (!isEmpty() && t != null) {
            return false;
        }

        p = t;
        if(t != null)
            getChildren().add(t); //Adding the image onto the tile
        else {
            getChildren().removeAll();
        }
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public boolean isEmpty() {
        return p == null;
    }

    public void deactivate(){ //This gets rid of all the rectangles on the Tile
        for (int i = 0; i < getChildren().size(); i++) {
            if (getChildren().get(i) instanceof Rectangle) {
                getChildren().remove(getChildren().get(i));
                i--;
            }

        }
    }


    public void setActive(boolean b) {

        deactivate();

        if (b && !isEmpty()) {
            Rectangle r = new Rectangle(39, 39, Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            r.setStrokeWidth(3);
            this.getChildren().add(r);
        }
    }

    public boolean isPass(){
        return pass;
    }

    public Piece removePiece() {
        Piece piece = getPiece();

        while (getChildren().size() > 1) {
            getChildren().remove(getChildren().size()-1);
        }

        p = null;

        return piece;
    }
}
