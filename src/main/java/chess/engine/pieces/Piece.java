package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import exception.InvalidMoveException;
import myutil.MyPair;

/**
 * Piece of the board
 * @author Andrea Galvan
 * @version 1.10
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
    public ColourEnum getColour(){
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
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * Returns all possible moves that the piece could do
     * @return list of all possible moves
     */
    public abstract LinkedList<MyPair<Integer, Integer>> possibleMoves();

    /**
     * Move to piece to parameter position
     * @throws InvalidMoveException thrown when the final position isn't a valid move
     */
    public void move(MyPair<Integer, Integer> position) throws InvalidMoveException{
        //check if the move is valid
        LinkedList<MyPair<Integer, Integer>> possibleMoves = possibleMoves();
        for(int i = 0; i < possibleMoves.size(); i++){
            if(possibleMoves.get(i).equals(position))
                break;
            
            if(i == possibleMoves.size() -1)    //last move
                throw new InvalidMoveException();
        }
        if(possibleMoves.size() == 0)
            throw new InvalidMoveException();

        //move the piece
        Board boardReference = board;
        board.removePiece(this);
        this.position = position;
        boardReference.addPiece(this);
    }

    @Override
    public String toString(){
        return "Position: " + position.toString() + "\n" +
            "Colour: " + colour + "\n" +
            "Piece: " + piece + "\n";
    }
}