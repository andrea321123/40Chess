package chess.engine.pieces;

import org.junit.Test;
import chess.engine.*;
import chess.engine.ColourEnum;
import myutil.MyPair;
import static org.junit.Assert.*;

/**
 * @author Andrea Galvan
 * @version 1.1
 */
public class PawnTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        Pawn test = new Pawn(ColourEnum.BLACK, board, new MyPair<>(1, 5));
        assertTrue(test.possibleMoves().size() == 2);

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(2, 6)));
        assertTrue(test.possibleMoves().size() == 3);

        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 6)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 5)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 5)));

        test = new Pawn(ColourEnum.WHITE, board, new MyPair<>(6, 1));

        assertTrue(test.possibleMoves().size() == 2);

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(5, 2)));
        board.addPiece(new Bishop(ColourEnum.BLACK, board, new MyPair<>(5, 0)));
        assertTrue(test.possibleMoves().size() == 3);

        board.addPiece(new Bishop(ColourEnum.BLACK, board, new MyPair<>(4, 1)));
        assertTrue(test.possibleMoves().size() == 2);


        assertTrue(test.possibleMoves().contains(new MyPair<>(5, 0)));
        
        test.move(new MyPair<>(5, 1));
        board.removePiece(board.getGrid()[4][1]);
        assertTrue(test.possibleMoves().size() == 1);
        board.addPiece(new Bishop(ColourEnum.BLACK, board, new MyPair<>(4, 2)));
        assertTrue(test.possibleMoves().size() == 2);
    }
}