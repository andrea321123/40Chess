package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import myutil.MyPair;

/**
 * Implements a knight piece
 * @author Andrea Galvan
 * @version 1.1
 */
public class Knight extends Piece {
    /**
     * Main constructor
     * @param colour   knight's colour
     * @param board    knight's board
     * @param position knight's position
     */
    public Knight(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        super(colour, board, position);
        piece = PieceEnum.KNIGHT;
    }
    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public Knight(Knight knight, Board board) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        super(knight, board);
        piece = PieceEnum.KNIGHT;
    }

    @Override
    public LinkedList<MyPair<Integer, Integer>> possibleMoves() {
        int iPos = getPosition().getFirst();
        int jPos = getPosition().getSecond();

        LinkedList<MyPair<Integer,Integer>> moves = new LinkedList<>();
        LinkedList<MyPair<Integer,Integer>> allMoves = new LinkedList<>();

        allMoves.add(new MyPair<>(iPos -2, jPos +1));
        allMoves.add(new MyPair<>(iPos -1, jPos +2));
        allMoves.add(new MyPair<>(iPos +1, jPos +2));
        allMoves.add(new MyPair<>(iPos +2, jPos +1));
        allMoves.add(new MyPair<>(iPos +2, jPos -1));
        allMoves.add(new MyPair<>(iPos +1, jPos -2));
        allMoves.add(new MyPair<>(iPos -1, jPos -2));
        allMoves.add(new MyPair<>(iPos -2, jPos -1));

        for(int i = 0; i < allMoves.size(); i++)
            try{
                if(getBoard().getGrid()[allMoves.get(i).getFirst()][allMoves.get(i).getFirst()].getColor() != getColor())
                    moves.add(allMoves.get(i));
            }
            catch(Exception e){}    //NullPointerException or ArrayIndexOutOfBoundsException
        
        return moves;
    }
}