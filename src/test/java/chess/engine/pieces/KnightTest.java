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
public class KnightTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        Knight test = new Knight(ColourEnum.BLACK, board, new MyPair<>(1, 6));

        assertTrue(test.possibleMoves().size() == 4);
    
        assertTrue(test.possibleMoves().contains(new MyPair<>(0, 4)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 4)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 5)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(3, 7)));

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(2, 4)));
        board.addPiece(new Queen(ColourEnum.BLACK, board, new MyPair<>(3, 7)));

        assertTrue(test.possibleMoves().size() == 3);
        assertTrue(!test.possibleMoves().contains(new MyPair<>(3, 7)));
        assertTrue(test.possibleMoves().contains(new MyPair<>(2, 4)));
    }
}