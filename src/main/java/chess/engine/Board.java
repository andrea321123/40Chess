package chess.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import chess.engine.pieces.*;
import myutil.MyPair;

/**
 * Implementation of a chess board
 * 
 * @author Andrea Galvan
 * @version 1.5
 */
public class Board {
    // fields
    public static final int SIZE = 8;
    private Piece[][] grid;

    /**
     * Default constructor
     */
    public Board() {
        grid = new Piece[SIZE][SIZE];
    }
    /**
     * Copy constructor
     * 
     * @param board board to copy
     */
    public Board(Board board) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        grid = new Piece[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                if(board.grid[i][j] != null)
                    grid[i][j] = board.grid[i][j].getClass().getConstructor(board.grid[i][j].getClass(), Board.class).newInstance(board.grid[i][j], this);
    }

    //getter/setter
    public Piece[][] getGrid(){
        return grid;
    }

    /**
     * Initializes the grid with standard starting configuration pieces
     */
    public void initialize(){
        for(int j = 0; j < SIZE; j++){      //pawns
            addPiece(new Pawn(ColourEnum.WHITE, this, new MyPair<>(SIZE -2, j)));
            addPiece(new Pawn(ColourEnum.BLACK, this, new MyPair<>(1, j)));
        }
        
        //other pieces
        addPiece(new Rook(ColourEnum.WHITE, this, new MyPair<>(7, 0)));
        addPiece(new Rook(ColourEnum.WHITE, this, new MyPair<>(7, 7)));
        addPiece(new Knight(ColourEnum.WHITE, this, new MyPair<>(7, 1)));
        addPiece(new Knight(ColourEnum.WHITE, this, new MyPair<>(7, 6)));
        addPiece(new Bishop(ColourEnum.WHITE, this, new MyPair<>(7, 2)));
        addPiece(new Bishop(ColourEnum.WHITE, this, new MyPair<>(7, 5)));
        addPiece(new King(ColourEnum.WHITE, this, new MyPair<>(7, 4)));
        addPiece(new Queen(ColourEnum.WHITE, this, new MyPair<>(7, 3)));

        addPiece(new Rook(ColourEnum.BLACK, this, new MyPair<>(0, 0)));
        addPiece(new Rook(ColourEnum.BLACK, this, new MyPair<>(0, 7)));
        addPiece(new Knight(ColourEnum.BLACK, this, new MyPair<>(0, 1)));
        addPiece(new Knight(ColourEnum.BLACK, this, new MyPair<>(0, 6)));
        addPiece(new Bishop(ColourEnum.BLACK, this, new MyPair<>(0, 2)));
        addPiece(new Bishop(ColourEnum.BLACK, this, new MyPair<>(0, 5)));
        addPiece(new King(ColourEnum.BLACK, this, new MyPair<>(0, 4)));
        addPiece(new Queen(ColourEnum.BLACK, this, new MyPair<>(0, 3)));
    }

    /**
     * Add piece parameter to the board
     * @param piece piece to add
     */
    public void addPiece(Piece piece){
        grid[piece.getPosition().getFirst()][piece.getPosition().getSecond()] = piece;
        piece.setBoard(this);
    }
    /**
     * Remove piece at position parameter
     * @param position position of the piece to remove
     */
    public void removePiece(Piece piece){
        if(piece.getBoard() == this){
            grid[piece.getPosition().getFirst()][piece.getPosition().getSecond()] = null;
            piece.setBoard(null);
        }
    }

    /**
     * Returns a list containing all pieces of colour parameter
     * @param colour colour of the pieces to be searched
     * @return black pieces
     */
    public LinkedList<Piece> piecesByColour(ColourEnum colour){
        LinkedList<Piece> pieces = new LinkedList<>();

        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                try{
                    if(grid[i][j].getColour() == colour)
                        pieces.add(grid[i][j]);
                } catch (NullPointerException e) {}

        return pieces;
    }

    @Override
    public String toString() {
        String string = "";

        for (int i = 0; i < SIZE; i++) {
            string += "|";
            for (int j = 0; j < SIZE; j++) {
                try {
                    Piece nowPiece = grid[i][j];
                    switch (nowPiece.getPiece()) {
                        case KING:
                            string += nowPiece.getColour() == ColourEnum.WHITE ? "K " : "k ";
                            break;
                        case QUEEN:
                            string += nowPiece.getColour() == ColourEnum.WHITE ? "Q " : "q ";
                            break;
                        case BISHOP:
                            string += nowPiece.getColour() == ColourEnum.WHITE ? "B " : "b ";
                            break;
                        case ROOK:
                            string += nowPiece.getColour() == ColourEnum.WHITE ? "R " : "r ";
                            break;
                        case KNIGHT:
                            string += nowPiece.getColour() == ColourEnum.WHITE ? "H " : "h ";
                            break;
                        case PAWN:
                            string += nowPiece.getColour() == ColourEnum.WHITE ? "P " : "p ";
                            break;
                        default:
                            string += "  ";
                            break;
                    }
                }
                catch(NullPointerException e){
                    string += "  ";
                }
            }
            string += "|\n";
        }
        return string;
    }
}