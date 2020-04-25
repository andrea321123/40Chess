package chess.engine;

import java.util.LinkedList;
import myutil.*;

/**
 * Search the best move from a given position
 * @author Andrea Galvan
 * @version 1.0
 */
public class Search {
    private MyTree<Board> tree;
    public static final int DEPTH = 2;
    public int conta = 0;
    Evaluation evaluation;

    public Search(){
        tree = new MyTree<>();
        evaluation = new Evaluation();
    }

    public void setBoard(Board board){
        tree.setInfo(board);
    }

    public Move bestMove(Board board, ColourEnum colour){
        tree = new MyTree<>();
        int best = minimax(0, tree, colour);
        LinkedList<Move> moves = tree.getInfo().possibleMoves(colour);

        //for(int i = 0; i < moves.size(); i++)
            //if(tree.getChildren().get(i).getInfo())
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

    private int minimax(int depth, MyTree<Board> tree, ColourEnum colour){
        //exit condition
        if(depth == DEPTH +1 || tree.getInfo().possibleMoves(colour).size() == 0)
            return evaluation.evaluation(tree.getInfo());
        depth++;

        //recursive action
        LinkedList<Move> moves = tree.getInfo().possibleMoves(colour);
        LinkedList<Integer> evaluationsList = new LinkedList<>();

        //add child for each move
        for(int i = 0; i < moves.size(); i++){
            Board nextBoard;
            try{
                nextBoard = new Board(tree.getInfo());
            }
            catch(Exception e){
                nextBoard = new Board();
            }
            nextBoard.getGrid()[moves.get(i).getFrom().getFirst()][moves.get(i).getFrom().getSecond()].
                    move(moves.get(i).getTo());
            MyTree<Board> child = new MyTree<>(nextBoard);
            tree.addChild(child);
            evaluationsList.add(minimax(depth, new MyTree<>(nextBoard), oppositeColour(colour)));
        }

            System.out.println(conta++);
        //minimax application
        if(colour == ColourEnum.WHITE)
            return max(evaluationsList);
        else
            return min(evaluationsList);
    }

    public static void main(String[] a){
        Board b = new Board();
        Search search = new Search();
        b.initialize();
        System.out.println(search.minimax(0, new MyTree<>(b), ColourEnum.WHITE));
    }
}