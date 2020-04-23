package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import myutil.MyPair;

/**
 * Implements a rook piece
 * @author Andrea Galvan
 * @version 1.1
 */
public class Rook extends Piece {
    private boolean neverMoved;
    /**
     * Main constructor
     * @param colour   rook's colour
     * @param board    rook's board
     * @param position rook's position
     */
    public Rook(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        super(colour, board, position);
        piece = PieceEnum.ROOK;
        neverMoved = true;
    }
    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public Rook(Rook rook, Board board) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        super(rook, board);
        piece = PieceEnum.ROOK;
        neverMoved = rook.neverMoved;
    }

    @Override
    public LinkedList<MyPair<Integer, Integer>> possibleMoves() {
        LinkedList<MyPair<Integer,Integer>> moves = new LinkedList<>();

        moves.addAll(SampleMoves.horizontalMoves(this));
        moves.addAll(SampleMoves.verticalMoves(this));
        return moves;
    }
}