package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import chess.engine.*;
import myutil.MyPair;

/**
 * Piece of the board
 * @author Andrea Galvan
 * @version 1.4
 */
public abstract class Piece {
    // fields
    private ColourEnum colour;
    private Board board;
    private MyPair<Integer, Integer> position;
    protected PieceEnum piece;

    /**
     * Main constructor
     * @param colour   piece's colour
     * @param board    piece's board
     * @param position piece's position
     */
    public Piece(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        this.colour = colour;
        this.position = position;
        this.board = board;
        this.board.addPiece(this);
    }

    /**
     * Default constructor
     */
    public Piece() {
        colour = null;
        board = null;
        position = null;
    }

    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public Piece(Piece piece, Board board) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        position = new MyPair<>(piece.position.getFirst(), piece.position.getSecond());
        colour = piece.colour;
        this.piece = piece.piece;
        this.board = board;
        this.board.addPiece(this);
    }

    //getter/setter
    public ColourEnum getColor(){
        return colour;
    }
    public Board getBoard(){
        return board;
    }
    public PieceEnum getPiece(){
        return piece;
    }
    public MyPair<Integer, Integer> getPosition(){
        return position;
    }
}