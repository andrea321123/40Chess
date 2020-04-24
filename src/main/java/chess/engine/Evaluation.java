package chess.engine;

import java.util.LinkedList;
import chess.engine.pieces.Piece;
import chess.engine.pieces.PieceEnum;
import myutil.MyPair;

/**
 * Evaluates the possibility for a player to win
 * @author Andrea Galvan
 * @version 1.0
 */
public class Evaluation {
    private int pawn = 100;
    private int rook = 500;
    private int knight = 300;
    private int bishop = 350;
    private int queen = 900;
    private int king = 100000;

    private  int[][] whitePawnTable = {
        {0,  0,  0,  0,  0,  0,  0,  0,},
        {50, 50, 50, 50, 50, 50, 50, 50,},
        {10, 10, 20, 30, 30, 20, 10, 10,},
        {5,  5, 10, 25, 25, 10,  5,  5,},
        {0,  0,  0, 20, 20,  0,  0,  0,},
        {5, -5,-10,  0,  0,-10, -5,  5,},
        {5, 10, 10,-20,-20, 10, 10,  5,},
        {0,  0,  0,  0,  0,  0,  0,  0}
    };
    private  int[][] blackPawnTable = mirrorTable(whitePawnTable);
    private int[][] whiteRookTable = {
        {0,  0,  0,  0,  0,  0,  0,  0,},
        {5, 10, 10, 10, 10, 10, 10,  5,},
       {-5,  0,  0,  0,  0,  0,  0, -5,},
       {-5,  0,  0,  0,  0,  0,  0, -5,},
       {-5,  0,  0,  0,  0,  0,  0, -5,},
       {-5,  0,  0,  0,  0,  0,  0, -5,},
       {-5,  0,  0,  0,  0,  0,  0, -5,},
       {0,  0,  0,  5,  5,  0,  0,  0}
    };
    private  int[][] blackRookTable = mirrorTable(whiteRookTable);
    private int[][] whiteKnightTable = {
        {-50,-40,-30,-30,-30,-30,-40,-50,},
        {-40,-20,  0,  0,  0,  0,-20,-40,},
        {-30,  0, 10, 15, 15, 10,  0,-30,},
        {-30,  5, 15, 20, 20, 15,  5,-30,},
        {-30,  0, 15, 20, 20, 15,  0,-30,},
        {-30,  5, 10, 15, 15, 10,  5,-30,},
        {-40,-20,  0,  5,  5,  0,-20,-40,},
        {-50,-40,-30,-30,-30,-30,-40,-50}
    };
    private  int[][] blackKnightTable = mirrorTable(whiteKnightTable);

    private int[][] whiteBishopTable = {
        {-20,-10,-10,-10,-10,-10,-10,-20,},
        {-10,  0,  0,  0,  0,  0,  0,-10,},
        {-10,  0,  5, 10, 10,  5,  0,-10,},
        {-10,  5,  5, 10, 10,  5,  5,-10,},
        {-10,  0, 10, 10, 10, 10,  0,-10,},
        {-10, 10, 10, 10, 10, 10, 10,-10,},
        {-10,  5,  0,  0,  0,  0,  5,-10,},
        {-20,-10,-10,-10,-10,-10,-10,-20,},
    };
    private  int[][] blackBishopTable = mirrorTable(whiteBishopTable);
    private int[][] whiteQueenTable = {
        {-20,-10,-10, -5, -5,-10,-10,-20,},
        {-10,  0,  0,  0,  0,  0,  0,-10,},
        {-10,  0,  5,  5,  5,  5,  0,-10,},
        {-5,  0,  5,  5,  5,  5,  0, -5,},
        {0,  0,  5,  5,  5,  5,  0, -5,},
        {-10,  5,  5,  5,  5,  5,  0,-10,},
        {-10,  0,  5,  0,  0,  0,  0,-10,},
        {-20,-10,-10, -5, -5,-10,-10,-20}
    };
    private  int[][] blackQueenTable = mirrorTable(whiteQueenTable);
    private int[][] whiteKingTable = {
        {-30,-40,-40,-50,-50,-40,-40,-30,},
        {-30,-40,-40,-50,-50,-40,-40,-30,},
        {-30,-40,-40,-50,-50,-40,-40,-30,},
        {-30,-40,-40,-50,-50,-40,-40,-30,},
        {-20,-30,-30,-40,-40,-30,-30,-20,},
        {-10,-20,-20,-20,-20,-20,-20,-10,},
        {20, 20,  0,  0,  0,  0, 20, 20,},
        {20, 30, 10,  0,  0, 10, 30, 20}
    };
    private  int[][] blackKingTable = mirrorTable(whiteKingTable);

    /**
     * Default constructor
     */
    public Evaluation(){
        blackPawnTable = mirrorTable(whitePawnTable);
    }

    //returns the mirror of a table
    private int[][] mirrorTable(int[][] table){
        int[][] mirror = new int[table.length][table.length];
        for(int i = 1; i <= table.length; i++)
            for(int j = 1; j <= table.length; j++)
                mirror[table.length - i][table.length - j] = table[i -1][j -1];
        return mirror;
    }

    //return the correct piece-square table
    private  int[][] pieceSquareTable(PieceEnum piece, ColourEnum colour){
        switch(piece){
            case PAWN:
                return colour == ColourEnum.WHITE ? whitePawnTable : blackPawnTable;
            case ROOK:
                return colour == ColourEnum.WHITE ? whiteRookTable : blackRookTable;
            case KNIGHT:
                return colour == ColourEnum.WHITE ? whiteKnightTable : blackKnightTable;
            case BISHOP:
                return colour == ColourEnum.WHITE ? whiteBishopTable : blackBishopTable;
            case QUEEN:
                return colour == ColourEnum.WHITE ? whiteQueenTable : blackQueenTable;
            case KING:
                return colour == ColourEnum.WHITE ? whiteKingTable : blackKingTable;
            default:
                return null;
        }
    }

    //Returns the algebraic sum of the piece values of a board parameter
    protected int pieceEvaluation(Board board){
        int ret = 0;
        LinkedList<Piece> pieces = board.piecesByColour(ColourEnum.WHITE);
        pieces.addAll(board.piecesByColour(ColourEnum.BLACK));

        for(int i = 0; i < pieces.size(); i++){
            int plusMinus = pieces.get(i).getColour() == ColourEnum.WHITE ? 1 : -1;
            switch(pieces.get(i).getPiece()){
                case PAWN:
                    ret += (pawn * plusMinus);
                    break;
                case ROOK:
                    ret += (rook * plusMinus);
                    break;
                case KNIGHT:
                    ret += (knight * plusMinus);
                    break;
                case BISHOP:
                    ret += (bishop * plusMinus);
                    break;
                case QUEEN:
                    ret += (queen * plusMinus);
                    break;
                case KING:
                    ret += (king * plusMinus);
                    break;
                default:
                    break;
            }
        }
        return ret;
    }

    //return the algebraic sum of the position values of all pieces
    protected int pieceSquareTableEvaluation(Board board){
        int ret = 0;
        LinkedList<Piece> pieces = board.piecesByColour(ColourEnum.WHITE);
        pieces.addAll(board.piecesByColour(ColourEnum.BLACK));

        for(int i = 0; i < pieces.size(); i++){
            int plusMinus = pieces.get(i).getColour() == ColourEnum.WHITE ? 1 : -1;
            MyPair<Integer, Integer> position = pieces.get(i).getPosition();
            ret += (pieceSquareTable(pieces.get(i).getPiece(), pieces.get(i).getColour())[position.getFirst()][position.getSecond()] * plusMinus);
        }
        return ret;
    }

    public  int evaluation(Board board){
        return pieceEvaluation(board) + pieceSquareTableEvaluation(board);
    }
}