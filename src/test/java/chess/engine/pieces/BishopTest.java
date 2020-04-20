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
public class BishopTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        Bishop test = new Bishop(ColourEnum.WHITE, board, new MyPair<>(5, 6));

        assertTrue(test.possibleMoves().size() == 9);
        assertTrue(test.possibleMoves().contains(new MyPair<>(6, 7)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 3)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(7, 4)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(4, 7)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(6, 6)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(1, 4)));

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(3, 4)));
        board.addPiece(new Queen(ColourEnum.BLACK, board, new MyPair<>(7, 4)));

        assertTrue(test.possibleMoves().size() == 5);
        assertTrue(test.possibleMoves().contains(new MyPair<>(7, 4)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(3, 4)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(1, 2)));

        board.removePiece(board.getGrid()[3][4]);
        board.removePiece(board.getGrid()[7][4]);
        assertTrue(test.possibleMoves().size() == 9);
    }
}