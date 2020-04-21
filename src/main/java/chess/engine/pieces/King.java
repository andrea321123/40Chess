package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import exception.InvalidMoveException;
import myutil.MyPair;

/**
 * Implements a king piece
 * @author Andrea Galvan
 * @version 1.2
 */
public class King extends Piece {
    private boolean neverMoved;

    /**
     * Main constructor
     * @param colour   king's colour
     * @param board    king's board
     * @param position king's position
     */
    public King(ColourEnum colour, Board board, MyPair<Integer, Integer> position) {
        super(colour, board, position);
        piece = PieceEnum.KING;
        neverMoved = true;
    }
    /**
     * Copy constructor; copy the piece on the given board
     * @param piece piece to copy
     * @param board board where to put the piece
     */
    public King(King king, Board board) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        super(king, board);
        piece = PieceEnum.KING;
        neverMoved = true;
    }

    @Override
    public LinkedList<MyPair<Integer, Integer>> possibleMoves(){
        LinkedList<MyPair<Integer, Integer>> moves = new LinkedList<>();
        int iPos = getPosition().getFirst();
        int jPos = getPosition().getSecond();

        for(int i = iPos -1; i <= iPos +1; i++)
            for(int j = jPos -1; j <= jPos +1; j++)
                try{
                    if(getBoard().getGrid()[i][j].getColour() != getColour())
                        moves.add(new MyPair<>(i, j));
                }
                catch(NullPointerException e){
                    moves.add(new MyPair<>(i, j));
                }
                catch(ArrayIndexOutOfBoundsException e){}
        
    //TODO: Il re non può andare in case sotto scacco
    //TODO Aggiungere l'arrocco
        return moves;
    }

    @Override
    public void move(MyPair<Integer, Integer> position) throws InvalidMoveException{
        super.move(position);
        neverMoved = false;
    }
}