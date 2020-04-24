package chess.engine;

import org.junit.Test;
import static org.junit.Assert.*;
import myutil.MyPair;
import chess.engine.pieces.*;

/**
 * @author Andrea Galvan
 * @version 1.1
 */
public class EvaluationTest {
    @Test
    public void evaluationTest(){
        Evaluation test = new Evaluation();
        Board board = new Board();
        board.initialize();
        assertTrue(test.evaluation(board) == 0);

        board = new Board();
        board.addPiece(new King(ColourEnum.WHITE, board, new MyPair<>(3, 5)));
        assertTrue(test.evaluation(board) == 100000 -40);
        board.addPiece(new King(ColourEnum.BLACK, board, new MyPair<>(0, 0)));
        assertTrue(test.evaluation(board) == -40 -20);

        board = new Board();
        board.addPiece(new Pawn(ColourEnum.WHITE, board, new MyPair<>(5, 5)));
        board.addPiece(new Rook(ColourEnum.BLACK, board, new MyPair<>(3, 5)));
        assertTrue(test.evaluation(board) == 100 -10 - 500 -0);

        board = new Board();
        board.addPiece(new Pawn(ColourEnum.WHITE, board, new MyPair<>(2, 1)));
        board.addPiece(new Pawn(ColourEnum.WHITE, board, new MyPair<>(2, 2)));
        board.addPiece(new Pawn(ColourEnum.WHITE, board, new MyPair<>(2, 3)));
        board.addPiece(new Bishop(ColourEnum.BLACK, board, new MyPair<>(3, 7)));
        assertTrue(test.evaluation(board) == 100+100+100+10+20+30-350+10);

        board = new Board();
        board.addPiece(new Queen(ColourEnum.WHITE, board, new MyPair<>(2, 3)));
        board.addPiece(new Queen(ColourEnum.BLACK, board, new MyPair<>(0, 0)));
        assertTrue(test.evaluation(board) == 5 + 20);
    }
}