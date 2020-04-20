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
public class RookTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        Rook test = new Rook(ColourEnum.BLACK, board, new MyPair<>(3, 2));

        assertTrue(test.possibleMoves().size() == 14);
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 0)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(6, 2)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 4)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 2)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(0, 1)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(2, 5)));

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(3, 6)));
        board.addPiece(new Queen(ColourEnum.BLACK, board, new MyPair<>(6, 2)));
        board.addPiece(new Queen(ColourEnum.BLACK, board, new MyPair<>(6, 5)));

        assertTrue(test.possibleMoves().size() == 11);
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 6)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(6, 2)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(3, 7)));
    }
}