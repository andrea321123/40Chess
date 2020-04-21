package chess.engine.pieces;

import org.junit.Test;
import chess.engine.*;
import chess.engine.ColourEnum;
import myutil.MyPair;
import static org.junit.Assert.*;

/**
 * @author Andrea Galvan
 * @version 1.0
 */
public class KingTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        King test = new King(ColourEnum.BLACK, board, new MyPair<>(1, 5));
        assertTrue(test.possibleMoves().size() == 8);

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(0, 5)));
        board.addPiece(new Bishop(ColourEnum.BLACK, board, new MyPair<>(0, 4)));
        assertTrue(test.possibleMoves().size() == 7);
        assertTrue(test.possibleMoves().contains(new MyPair<>(0, 5)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(0, 4)));
    }
}