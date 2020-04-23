package chess.engine.pieces;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.*;
import exception.InvalidMoveException;
import myutil.MyPair;

/**
 * Implements a king piece
 * @author Andrea Galvan
 * @version 1.4
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
        neverMoved = king.neverMoved;
    }

    //check if there is check in a position parameter
    private boolean checkIn(MyPair<Integer, Integer> position){
        Board copy;
        try{
            copy = new Board(getBoard());
        }
        catch(Exception e){
            System.out.println("AAA");
            return true;
        }

        copy.removePiece(copy.kingByColour(getColour()));
        copy.addPiece(new King(getColour(), copy, position));
        if(copy.check() == getColour())
            return true;
        return false;
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
                        if(!checkIn(new MyPair<>(i, j)))
                            moves.add(new MyPair<>(i, j));
                }
                catch(NullPointerException e){
                    if(!checkIn(new MyPair<>(i, j)))
                        moves.add(new MyPair<>(i, j));
                }
                catch(ArrayIndexOutOfBoundsException e){}

                //remove possible moves of the opposite king
                ColourEnum oppositeColour;
                if(getColour() == ColourEnum.WHITE)
                    oppositeColour = ColourEnum.BLACK;
                else
                    oppositeColour = ColourEnum.WHITE;
                MyPair<Integer, Integer> oppositeKingPosition = getBoard().kingByColour(oppositeColour).getPosition();
                for(int i = oppositeKingPosition.getFirst() -1; i <= oppositeKingPosition.getFirst() +1; i++)
                    for(int j = oppositeKingPosition.getSecond() -1; j <= oppositeKingPosition.getSecond() +1; j++)
                        moves.remove(new MyPair<>(i, j));

    //TODO Aggiungere l'arrocco
        return moves;
    }

    @Override
    public void move(MyPair<Integer, Integer> position) throws InvalidMoveException{
        super.move(position);
        neverMoved = false;
    }
}