package chess.engine;

import java.util.LinkedList;
import java.util.HashMap;
import myutil.*;

/**
 * Search the best move from a given position
 * @author Andrea Galvan
 * @version 2.0
 */
public class Search {
    private MyTree<MyPair<Board, Integer>> tree;
    private HashMap<Board, Move> transpositionTable;
    public static final int DEPTH = 3;
    static int conta = 0;
    Evaluation evaluation;
    
    public Search(){
        conta = 0;
        tree = new MyTree<>();
        transpositionTable = new HashMap<>();
        evaluation = new Evaluation();
    }

    public Move bestMove(Board board, ColourEnum colour){
        System.out.println("HAsh SiZE: " + transpositionTable.size());
        //check if transposition tables can be used
        if(transpositionTable.containsKey(board)){
            System.out.println("Used transposition table");
            return transpositionTable.get(board);
        }
        conta = 0;
        tree = new MyTree<>(new MyPair<>(board, 0));
        int best = minimax(0, tree, colour, -1000000, 1000000);
        LinkedList<Move> moves = tree.getInfo().getFirst().possibleMoves(colour);

        for(int i = 0; i < moves.size(); i++)
            if(tree.getChildren().get(i).getInfo().getSecond() == best)
                return moves.get(i);
        return null;
    }

    private int min(int n1, int n2){
        if(n1 <= n2)
            return n1;
        return n2;
    }

    private int max(int n1, int n2){
        if(n1 >= n2)
            return n1;
        return n2;
    }

    private ColourEnum oppositeColour(ColourEnum colour){
        if(colour == ColourEnum.WHITE)
            return ColourEnum.BLACK;
        return ColourEnum.WHITE;
    }

    private int minimax(int depth, MyTree<MyPair<Board, Integer>> tree, ColourEnum colour, int alpha, int beta){
        Board hashBoard = tree.getInfo().getFirst();
        Move hashMove = null;
        //exit condition
        if(depth == DEPTH +1 || tree.getInfo().getFirst().possibleMoves(colour).size() == 0){
            int ret = evaluation.evaluation(tree.getInfo().getFirst());
            tree.getInfo().setSecond(ret);
            return evaluation.evaluation(tree.getInfo().getFirst());
        }
        depth++;
        
        //recursive action
        LinkedList<Move> moves = tree.getInfo().getFirst().possibleMoves(colour);

        if(colour == ColourEnum.WHITE){     //maximize
            int bestvalue = -1000000;
            for(int i = 0; i < moves.size(); i++){
                //add the child
                Board nextBoard;
                try{
                    nextBoard = new Board(tree.getInfo().getFirst());
                }
                catch(Exception e){
                    nextBoard = new Board();
                }
                nextBoard.getGrid()[moves.get(i).getFrom().getFirst()][moves.get(i).getFrom().getSecond()].
                    move(moves.get(i).getTo());
                
                MyTree<MyPair<Board, Integer>> child = new MyTree<>(new MyPair<>(nextBoard, 0));
                tree.addChild(child);
                int value = minimax(depth, child, oppositeColour(colour), alpha, beta);
                if(value > bestvalue)
                    hashMove = moves.get(i);    //update move for transposition table
                bestvalue = max(bestvalue, value);
                alpha = max(alpha, bestvalue);
                if(beta <= alpha)
                    break;
            }
            tree.getInfo().setSecond(bestvalue);
            transpositionTable.put(hashBoard, hashMove);
            return bestvalue;
        }
        else{       //minimize
            int bestvalue = 1000000;
            for(int i = 0; i < moves.size(); i++){
                //add the child
                Board nextBoard;
                try{
                    nextBoard = new Board(tree.getInfo().getFirst());
                }
                catch(Exception e){
                    nextBoard = new Board();
                }
                nextBoard.getGrid()[moves.get(i).getFrom().getFirst()][moves.get(i).getFrom().getSecond()].
                    move(moves.get(i).getTo());
                
                MyTree<MyPair<Board, Integer>> child = new MyTree<>(new MyPair<>(nextBoard, 0));
                tree.addChild(child);
                int value = minimax(depth, child, oppositeColour(colour), alpha, beta);
                if(value < bestvalue)
                    hashMove = moves.get(i);    //update move for transposition table
                bestvalue = min(bestvalue, value);
                beta = min(beta, bestvalue);
                if(beta <= alpha)
                    break;   
            }
            tree.getInfo().setSecond(bestvalue);
            transpositionTable.put(hashBoard, hashMove);
            return bestvalue;
        }
    }
}