import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * Created by 39441 on 9/8/14.
 */
public class Board extends GridPane {
    public static int ROWS = 10;
    public static int COLS = 9;
    private Tile[][] tiles;

    public Board(Scene scene) {
        tiles = new Tile[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(i == 5 && (j == 4 || j == 6 || j == 2) || i == 4 && (j == 4 || j == 6 || j == 2)) { //The tiles at those locations must be impassable
                    tiles[i][j] = new Tile(false, i, j);
                } else { //All other tiles are passable
                    tiles[i][j] = new Tile(true, i, j);
                }
                this.add(tiles[i][j], j, i);


            }
        }
    }


    public Tile getTile(int r, int c) {
        if (r < 0 || r >= ROWS || c <0 || c >= COLS)
            return null;

        return tiles[r][c];
    }
}
