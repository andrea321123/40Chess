package chess.engine.pieces;

import chess.engine.*;
import myutil.MyPair;

/**
 * Piece of the board
 * @author Andrea Galvan
 * @version 1.1
 */
public abstract class Piece {
    //fields
    private ColourEnum colour;
    private Board board;
    private MyPair<Integer, Integer> position;

    /**
     * Main constructor
     * @param colour piece's colour
     * @param board piece's board
     * @param position piece's position
     */
    public Piece(ColourEnum colour, Board board, MyPair<Integer, Integer> position){
        this.colour = colour;
        this.board = board;
        this.position = position;
    }

    /**
     * Default constructor
     */
    public Piece(){
        colour = null;
        board = null;
        position = null;
    }

    /**
     * Copy constructor
     * @param piece piece to copy
     */
    public Piece(Piece piece){
        colour = piece.colour;
        board = new Board(piece.board);
        position = new MyPair<>(piece.position.getFirst(), piece.position.getSecond());
    }
}