package chess.engine;

import org.junit.Test;
import chess.engine.pieces.PieceEnum;
import chess.engine.pieces.*;
import myutil.MyPair;
import static org.junit.Assert.*;

/**
 * @author Andrea Galvan
 * @version 1.1
 */
public class BoardTest {
    @Test
    public void addPieceTest(){
        Board test = new Board();
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<Integer,Integer>(2, 1)));
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<Integer,Integer>(2, 2)));
        test.addPiece(new Queen(ColourEnum.BLACK, test, new MyPair<Integer,Integer>(5, 7)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<Integer,Integer>(6, 2)));

        assertTrue(test.getGrid()[2][1].getColor() == ColourEnum.WHITE);
        assertTrue(test.getGrid()[2][2].getPiece() == PieceEnum.QUEEN);
        assertTrue(test.getGrid()[5][7].getPosition().equals(new MyPair<>(5, 7)));
        assertTrue(test.getGrid()[6][2].getPiece() != PieceEnum.QUEEN);
    }
    @Test
    public void removePieceTest(){
        Board test = new Board();
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<Integer,Integer>(2, 1)));
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<Integer,Integer>(2, 2)));
        test.addPiece(new Queen(ColourEnum.BLACK, test, new MyPair<Integer,Integer>(5, 7)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<Integer,Integer>(6, 2)));

        test.removePiece(test.getGrid()[2][2]);

        assertTrue(test.getGrid()[2][1] != null);
        assertTrue(test.getGrid()[2][2] == null);
    }
}