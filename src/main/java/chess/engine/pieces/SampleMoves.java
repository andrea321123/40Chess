package chess.engine.pieces;

import java.util.LinkedList;
import chess.engine.*;
import myutil.MyPair;

/**
 * Contains functions that help build possibleMove() methods
 * @author Andrea Galvan
 * @version 1.1
 */
public class SampleMoves{
    private static Board board;
    private static MyPair<Integer, Integer> position;
    private static ColourEnum colour;

    //initialize the fields
    private static void initialization(Piece piece){
        board = piece.getBoard();
        position = piece.getPosition();
        colour = piece.getColour();
    }

    /**
     * Return a list of all horizontal possible moves
     * 
     * @param piece piece to check
     * @return horizontal moves
     */
    public static LinkedList<MyPair<Integer, Integer>> horizontalMoves(Piece piece) {
        initialization(piece);
        LinkedList<MyPair<Integer, Integer>> moves = new LinkedList<>();
        int tmpJ = position.getSecond() - 1;

        while (tmpJ >= 0) { // left moves
            if (board.getGrid()[position.getFirst()][tmpJ] == null)
                moves.add(new MyPair<>(position.getFirst(), tmpJ));
            else { // find a piece
                if (board.getGrid()[position.getFirst()][tmpJ].getColour() != colour)
                    moves.add(new MyPair<>(position.getFirst(), tmpJ));
                break;
            }
            tmpJ--;
        }
        tmpJ = position.getSecond() + 1;
        while (tmpJ < Board.SIZE) { // right moves
            if (board.getGrid()[position.getFirst()][tmpJ] == null)
                moves.add(new MyPair<>(position.getFirst(), tmpJ));
            else { // find a piece
                if (board.getGrid()[position.getFirst()][tmpJ].getColour() != colour)
                    moves.add(new MyPair<>(position.getFirst(), tmpJ));
                break;
            }
            tmpJ++;
        }

        return moves;
    }

    /**
     * Return a list of all vertical possible moves
     * 
     * @param piece to check
     * @return vertical moves
     */
    public static LinkedList<MyPair<Integer, Integer>> verticalMoves(Piece piece) {
        initialization(piece);
        LinkedList<MyPair<Integer, Integer>> moves = new LinkedList<>();
        int tmpI = position.getFirst() - 1;

        while (tmpI >= 0) { // up moves
            if (board.getGrid()[tmpI][position.getSecond()] == null)
                moves.add(new MyPair<>(tmpI, position.getSecond()));
            else { // find a piece
                if (board.getGrid()[tmpI][position.getSecond()].getColour() != colour)
                    moves.add(new MyPair<>(tmpI, position.getSecond()));
                break;
            }
            tmpI--;
        }
        tmpI = position.getFirst() + 1;
        while (tmpI < Board.SIZE) { // down moves
            if (board.getGrid()[tmpI][position.getSecond()] == null)
                moves.add(new MyPair<>(tmpI, position.getSecond()));
            else { // find a piece
                if (board.getGrid()[tmpI][position.getSecond()].getColour() != colour)
                    moves.add(new MyPair<>(tmpI, position.getSecond()));
                break;
            }
            tmpI++;
        }

        return moves;
    }

    /**
     * Return a list of all diagonal possible moves
     * 
     * @param piece piece to check
     * @return diagonal moves
     */
    public static LinkedList<MyPair<Integer, Integer>> diagonalMoves(Piece piece) {
        initialization(piece);
        LinkedList<MyPair<Integer, Integer>> moves = new LinkedList<>();
        int tmpI = position.getFirst() - 1;
        int tmpJ = position.getSecond() - 1;

        while (tmpI >= 0 && tmpJ >= 0) { // up left moves
            if (board.getGrid()[tmpI][tmpJ] == null)
                moves.add(new MyPair<>(tmpI, tmpJ));
            else { // find a piece
                if (board.getGrid()[tmpI][tmpJ].getColour() != colour)
                    moves.add(new MyPair<>(tmpI, tmpJ));
                break;
            }
            tmpI--;
            tmpJ--;
        }
        tmpI = position.getFirst() - 1;
        tmpJ = position.getSecond() + 1;
        while (tmpI >= 0 && tmpJ < Board.SIZE) { // up right moves
            if (board.getGrid()[tmpI][tmpJ] == null)
                moves.add(new MyPair<>(tmpI, tmpJ));
            else { // find a piece
                if (board.getGrid()[tmpI][tmpJ].getColour() != colour)
                    moves.add(new MyPair<>(tmpI, tmpJ));
                break;
            }
            tmpI--;
            tmpJ++;
        }
        tmpI = position.getFirst() + 1;
        tmpJ = position.getSecond() - 1;
        while (tmpI < Board.SIZE && tmpJ >= 0) { // down left moves
            if (board.getGrid()[tmpI][tmpJ] == null)
                moves.add(new MyPair<>(tmpI, tmpJ));
            else { // find a piece
                if (board.getGrid()[tmpI][tmpJ].getColour() != colour)
                    moves.add(new MyPair<>(tmpI, tmpJ));
                break;
            }
            tmpI++;
            tmpJ--;
        }
        tmpI = position.getFirst() + 1;
        tmpJ = position.getSecond() + 1;
        while (tmpI < Board.SIZE && tmpJ < Board.SIZE) { // down right moves
            if (board.getGrid()[tmpI][tmpJ] == null)
                moves.add(new MyPair<>(tmpI, tmpJ));
            else { // find a piece
                if (board.getGrid()[tmpI][tmpJ].getColour() != colour)
                    moves.add(new MyPair<>(tmpI, tmpJ));
                break;
            }
            tmpI++;
            tmpJ++;
        }

        return moves;
    }
}