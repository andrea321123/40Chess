package chess.engine;

import org.junit.Test;

import chess.engine.pieces.*;
import myutil.MyPair;
import static org.junit.Assert.*;

/**
 * @author Andrea Galvan
 * @version 1.4
 */
public class BoardTest {
    @Test
    public void addPieceTest(){
        Board test = new Board();
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<Integer,Integer>(2, 1)));
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<Integer,Integer>(2, 2)));
        test.addPiece(new Queen(ColourEnum.BLACK, test, new MyPair<Integer,Integer>(5, 7)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<Integer,Integer>(6, 2)));

        assertTrue(test.getGrid()[2][1].getColour() == ColourEnum.WHITE);
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
    @Test
    public void PiecesByColourTest(){
        Board test = new Board();
        test.initialize();
        
        assertTrue(test.piecesByColour(ColourEnum.WHITE).size() == 16);
        assertTrue(test.piecesByColour(ColourEnum.BLACK).size() == 16);

        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<>(4, 4)));
        assertTrue(test.piecesByColour(ColourEnum.WHITE).size() == 17);
    }
    @Test
    public void kingByColourTest(){
        Board test = new Board();
        test.initialize();
        assertTrue(test.kingByColour(ColourEnum.WHITE) == test.getGrid()[7][4]);
        assertTrue(test.kingByColour(ColourEnum.BLACK) == test.getGrid()[0][4]);
    }
    @Test
    public void checkTest(){
        Board test = new Board();
        test.addPiece(new King(ColourEnum.BLACK, test, new MyPair<>(7, 7)));
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<>(5, 7)));
        test.addPiece(new King(ColourEnum.WHITE, test, new MyPair<>(5, 0)));
        assertTrue(test.check() == ColourEnum.BLACK);

        test = new Board();
        test.addPiece(new King(ColourEnum.WHITE, test, new MyPair<>(7, 7)));
        test.addPiece(new King(ColourEnum.BLACK, test, new MyPair<>(0, 0)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<>(6, 0)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<>(7, 0)));
        assertTrue(test.check() == ColourEnum.WHITE);
        test.removePiece(test.getGrid()[7][0]);
        assertTrue(test.check() == null);

        test = new Board();
        test.addPiece(new King(ColourEnum.WHITE, test, new MyPair<>(7, 7)));
        test.addPiece(new King(ColourEnum.BLACK, test, new MyPair<>(0, 0)));
        assertTrue(test.check() == null);
        test.addPiece(new Bishop(ColourEnum.BLACK, test, new MyPair<>(1, 1)));
        assertTrue(test.check() == ColourEnum.WHITE);

        test = new Board();
        test.addPiece(new King(ColourEnum.WHITE, test, new MyPair<>(7, 7)));
        test.addPiece(new King(ColourEnum.BLACK, test, new MyPair<>(0, 0)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<>(0, 6)));
        assertTrue(test.check() == null);
        test.getGrid()[0][6].move(new MyPair<>(0,7));
        assertTrue(test.check() == ColourEnum.WHITE);
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<>(3, 7)));
        assertTrue(test.check() == null);
        test.getGrid()[0][7].move(new MyPair<>(3,7));
        assertTrue(test.check() == ColourEnum.WHITE);
    }
    @Test
    public void checkMateTest(){
        Board test = new Board();

        test.addPiece(new King(ColourEnum.WHITE, test, new MyPair<>(7, 7)));
        test.addPiece(new King(ColourEnum.BLACK, test, new MyPair<>(0, 0)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<>(6, 0)));
        test.addPiece(new Rook(ColourEnum.BLACK, test, new MyPair<>(7, 0)));
        assertTrue(test.checkMate() == ColourEnum.WHITE);

        test = new Board();
        test.addPiece(new King(ColourEnum.WHITE, test, new MyPair<>(2, 1)));
        test.addPiece(new King(ColourEnum.BLACK, test, new MyPair<>(0, 0)));
        assertTrue(test.checkMate() == null);
        test.addPiece(new Queen(ColourEnum.WHITE, test, new MyPair<>(1, 1)));

        assertTrue(test.checkMate() == ColourEnum.BLACK);
        
    }
}