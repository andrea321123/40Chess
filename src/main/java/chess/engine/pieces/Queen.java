package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import myutil.MyPair;

/**
 * Implements a queen piece
 * @author Andrea Galvan
 * @version 1.1
 */
public class Queen extends Piece {
    /**
     * Main constructor
     * @param colour   queen's colour
     * @param board    queen's board
     * @param position queen's position
     */
    public Queen(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        super(colour, board, position);
        piece = PieceEnum.QUEEN;
    }
    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public Queen(Queen queen, Board board) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        super(queen, board);
        piece = PieceEnum.QUEEN;
    }

    @Override
    public LinkedList<MyPair<Integer, Integer>> possibleMoves() {return null;}
}