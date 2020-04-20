package chess.engine;

import java.lang.reflect.InvocationTargetException;
import chess.engine.pieces.Piece;

/**
 * Implementation of a chess board
 * 
 * @author Andrea Galvan
 * @version 1.3
 */
public class Board {
    // fields
    public static final int SIZE = 8;
    private Piece[][] grid;

    /**
     * Default constructor
     */
    public Board() {
        grid = new Piece[SIZE][SIZE];
    }

    /**
     * Copy constructor
     * 
     * @param board board to copy
     */
    public Board(Board board) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        grid = new Piece[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                if(board.grid[i][j] != null)
                    grid[i][j] = board.grid[i][j].getClass().getConstructor(Piece.class, Board.class).newInstance(board.grid[i][j], this);
    }

    //getter/setter
    public Piece[][] getGrid(){
        return grid;
    }

    /**
     * Add piece parameter to the board
     * @param piece piece to add
     */
    public void addPiece(Piece piece){
        grid[piece.getPosition().getFirst()][piece.getPosition().getSecond()] = piece;
        piece.setBoard(this);
    }
    /**
     * Remove piece at position parameter
     * @param position position of the piece to remove
     */
    public void removePiece(Piece piece){
        if(piece.getBoard() == this){
            grid[piece.getPosition().getFirst()][piece.getPosition().getSecond()] = null;
            piece.setBoard(null);
        }
    }
}