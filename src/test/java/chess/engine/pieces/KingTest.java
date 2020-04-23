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
public class KingTest {
    @Test
    public void possibleMovesTest(){
        Board board = new Board();
        King test = new King(ColourEnum.BLACK, board, new MyPair<>(1, 5));
        board.addPiece(new King(ColourEnum.WHITE, board, new MyPair<>(0, 0)));
        assertTrue(test.possibleMoves().size() == 8);

        board.addPiece(new Bishop(ColourEnum.WHITE, board, new MyPair<>(0, 5)));
        board.addPiece(new Bishop(ColourEnum.BLACK, board, new MyPair<>(0, 4)));
        assertTrue(test.possibleMoves().size() == 5);
        assertTrue(test.possibleMoves().contains(new MyPair<>(0, 5)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(0, 4)));
        assertTrue(!test.possibleMoves().contains(new MyPair<>(1, 4)));

        board = new Board();
        test = new King(ColourEnum.WHITE, board, new MyPair<>(7, 7));
        board.addPiece(new King(ColourEnum.BLACK, board, new MyPair<>(0, 0)));
        board.addPiece(new Rook(ColourEnum.BLACK, board, new MyPair<>(7, 0)));
        board.addPiece(new Rook(ColourEnum.BLACK, board, new MyPair<>(6, 0)));
        assertTrue(test.possibleMoves().size() == 0);
        board.addPiece(new Pawn(ColourEnum.WHITE, board, new MyPair<>(6,4)));
        assertTrue(test.possibleMoves().size() == 2);
        board.addPiece(new Pawn(ColourEnum.WHITE, board, new MyPair<>(7,1)));
        assertTrue(test.possibleMoves().size() == 3);

        board = new Board();
        test = new King(ColourEnum.WHITE, board, new MyPair<>(7, 4));
        board.addPiece(new King(ColourEnum.BLACK, board, new MyPair<>(0, 4)));
        board.addPiece(new Rook(ColourEnum.WHITE, board, new MyPair<>(7, 0)));
        System.out.println(test.possibleMoves());
        assertTrue(test.possibleMoves().size() == 6);
        board.addPiece(new Rook(ColourEnum.WHITE, board, new MyPair<>(7, 7)));
        assertTrue(test.possibleMoves().size() == 7);
        board.getGrid()[7][7].move(new MyPair<>(7, 5));
        board.getGrid()[7][5].move(new MyPair<>(7, 7));
        assertTrue(test.possibleMoves().size() == 6);
        board.addPiece(new Rook(ColourEnum.BLACK, board, new MyPair<>(5, 3)));
        assertTrue(test.possibleMoves().size() == 3);
    }
}