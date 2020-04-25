package chess.engine;

import java.util.LinkedList;
import java.util.Scanner;

import myutil.*;

/**
 * Search the best move from a given position
 * @author Andrea Galvan
 * @version 1.1
 */
public class Search {
    private MyTree<MyPair<Board, Integer>> tree;
    public static final int DEPTH = 2;
    Evaluation evaluation;

    public Search(){
        tree = new MyTree<>();
        evaluation = new Evaluation();
    }

    public Move bestMove(Board board, ColourEnum colour){
        tree = new MyTree<>(new MyPair<>(board, 0));
        int best = minimax(0, tree, colour);
        LinkedList<Move> moves = tree.getInfo().getFirst().possibleMoves(colour);

        for(int i = 0; i < moves.size(); i++)
            if(tree.getChildren().get(i).getInfo().getSecond() == best)
                return moves.get(i);
            return null;
    }

    private int min(LinkedList<Integer> list){
        int min;
        try{
            min = list.get(0);
        }
        catch(Exception e){
            return 0;
        }

        for(int i = 1; i < list.size(); i++)
            if(list.get(i) < min)
                min = list.get(i);
        
        return min;
    }

    private int max(LinkedList<Integer> list){
        int max;
        try{
            max = list.get(0);
        }
        catch(Exception e){
            return 0;
        }

        for(int i = 1; i < list.size(); i++)
            if(list.get(i) > max)
                max = list.get(i);
        
        return max;
    }

    private ColourEnum oppositeColour(ColourEnum colour){
        if(colour == ColourEnum.WHITE)
            return ColourEnum.BLACK;
        return ColourEnum.WHITE;
    }

    private int minimax(int depth, MyTree<MyPair<Board, Integer>> tree, ColourEnum colour){
        //exit condition
        if(depth == DEPTH +1 || tree.getInfo().getFirst().possibleMoves(colour).size() == 0)
            return evaluation.evaluation(tree.getInfo().getFirst());
        depth++;

        //recursive action
        LinkedList<Move> moves = tree.getInfo().getFirst().possibleMoves(colour);
        LinkedList<Integer> evaluationsList = new LinkedList<>();

        //add child for each move
        for(int i = 0; i < moves.size(); i++){
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
            evaluationsList.add(minimax(depth, child, oppositeColour(colour)));
        }

        //minimax application
        int ret;
        if(colour == ColourEnum.WHITE){
            ret = max(evaluationsList);
            tree.getInfo().setSecond(ret);
        }
        else{
            ret = min(evaluationsList);
            tree.getInfo().setSecond(ret);
        }
        return ret;
    }
}