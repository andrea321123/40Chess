package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import exception.InvalidMoveException;
import myutil.MyPair;

/**
 * Implements a pawn piece
 * @author Andrea Galvan
 * @version 1.1
 */
public class Pawn extends Piece {
    private boolean neverMoved;

    /**
     * Main constructor
     * @param colour   pawn's colour
     * @param board    pawn's board
     * @param position pawn's position
     */
    public Pawn(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        super(colour, board, position);
        piece = PieceEnum.PAWN;
        neverMoved = true;
    }
    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public Pawn(Pawn pawn, Board board) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        super(pawn, board);
        piece = PieceEnum.PAWN;
        neverMoved = true;
    }

    @Override
    public LinkedList<MyPair<Integer, Integer>> possibleMoves() {
        int plusMinus;      //the offset to apply to i position to go to the front of the pawn
        if(getColor() == ColourEnum.WHITE)
            plusMinus = -1;
        else
            plusMinus = 1;
        int iPos = getPosition().getFirst();
        int jPos = getPosition().getSecond();

        LinkedList<MyPair<Integer, Integer>> moves = new LinkedList<>();

        
        if(iPos != 0 && getBoard().getGrid()[iPos + plusMinus][jPos] == null){
            moves.add(new MyPair<>(iPos +plusMinus, jPos));
            if(neverMoved && getBoard().getGrid()[iPos + (plusMinus *2)][jPos] == null)
                moves.add(new MyPair<>(iPos + (plusMinus *2), jPos));
        }

        for(int i = -1; i < 2; i += 2)  //both diagonal moves
            try{
                if(getBoard().getGrid()[iPos +plusMinus][jPos + i].getColor() != getColor())
                    moves.add(new MyPair<>(iPos + plusMinus, jPos + i));
            }
            catch(Exception e){}

        return moves;
    }

    @Override
    public void move(MyPair<Integer, Integer> position) throws InvalidMoveException{
        super.move(position);
        neverMoved = false;
    }
}