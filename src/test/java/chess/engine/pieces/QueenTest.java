package chess.engine.pieces;

import org.junit.Test;
import chess.engine.*;
import chess.engine.ColourEnum;
import myutil.MyPair;
import static org.junit.Assert.*;

/**
 * @author Andrea Galvan
 * @version 1.2
 */
public class QueenTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        Queen test = new Queen(ColourEnum.BLACK, board, new MyPair<>(2, 5));

        assertTrue(test.possibleMoves().size() == 25);
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 7)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(1, 6)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(0, 5)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(0, 3)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 2)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(5, 2)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(4, 5)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 6)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(4, 6)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(6, 2)));

        board.addPiece(new Queen(ColourEnum.WHITE, board, new MyPair<>(2, 3)));
        board.addPiece(new Queen(ColourEnum.BLACK, board, new MyPair<>(4, 7)));
        assertTrue(test.possibleMoves().size() == 21);
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 3)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(4, 7)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(2, 1)));

        board = new Board();
        board.initialize();
        test = (Queen)board.getGrid()[7][3];
        assertTrue(test.possibleMoves().size() == 0);
    }
}