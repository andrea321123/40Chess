package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import myutil.MyPair;

/**
 * Implements a bishop piece
 * @author Andrea Galvan
 * @version 1.0
 */
public class Bishop extends Piece {
    /**
     * Main constructor
     * @param colour   bishop's colour
     * @param board    bishop's board
     * @param position bishop's position
     */
    public Bishop(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        super(colour, board, position);
        piece = PieceEnum.BISHOP;
    }
    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public Bishop(Bishop bishop, Board board) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        super(bishop, board);
        piece = PieceEnum.BISHOP;
    }

    @Override
    public LinkedList<MyPair<Integer, Integer>> possibleMoves() {
        LinkedList<MyPair<Integer,Integer>> moves = new LinkedList<>();

        moves.addAll(SampleMoves.diagonalMoves(this));
        return moves;
    }
}