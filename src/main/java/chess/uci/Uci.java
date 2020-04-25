package chess.uci;

import chess.engine.*;
import chess.engine.Move;
import java.util.Scanner;
import myutil.MyPair;

/**
 * Interacts with UCI protocol
 * @author Andrea
 * @version 1.0
 */
public class Uci {
    public static Board board;
    public static Search search;
    public static final String ENGINE_NAME = "40CHESS";
    public static ColourEnum turn;
    public static void main(String[] args) {
        System.out.println(algebraToMove("d1h5"));
        board = new Board();
        search = new Search();
        turn = ColourEnum.WHITE;
        Scanner input = new Scanner(System.in);
        while (true)
        {
            String inputString=input.nextLine();
            if ("uci".equals(inputString))
            {
                inputUCI();
            }
            else if (inputString.startsWith("setoption"))
            {
                inputSetOption(inputString);
            }
            else if ("isready".equals(inputString))
            {
                inputIsReady();
            }
            else if ("ucinewgame".equals(inputString))
            {
                inputUCINewGame();
            }
            else if (inputString.startsWith("position"))
            {
                inputPosition(inputString);
            }
            else if (inputString.startsWith("go"))
            {
                inputGo();
            }
            else if ("print".equals(inputString))
            {
                inputPrint();
            }
        }
    }
    
    public static ColourEnum oppositeTurn(ColourEnum colour){
        if(colour == ColourEnum.WHITE)
            return ColourEnum.BLACK;
        else
            return ColourEnum.WHITE;
    }
    
    public static int letterToInt(char letter){
        switch(letter){
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return 0;
        }
        
    }
    public static String intToLetter(int num){
        switch(num){
            case 0:
                return "a";
            case 1:
                return "b";   
            case 2:
                return "c";   
            case 3:
                return "d";   
            case 4:
                return "e";   
            case 5:
                return "f";   
            case 6:
                return "g";   
            case 7:
                return "h";   
            default:
                return "a";
        }
    }
    public static Move algebraToMove(String algebra){
        Move move = new Move();
        
        move.setFrom(new MyPair<>(Board.SIZE - Character.getNumericValue(algebra.charAt(1)), letterToInt(algebra.charAt(0))));
        move.setTo(new MyPair<>(Board.SIZE - Character.getNumericValue(algebra.charAt(3)), letterToInt(algebra.charAt(2))));
        
        return move;
    }
    public static String moveToAlgebra(Move move){
        String algebra = "";
        algebra += intToLetter(move.getFrom().getSecond());
        algebra += Integer.toString(Board.SIZE - move.getFrom().getFirst());
        algebra += intToLetter(move.getTo().getSecond());
        algebra += Integer.toString(Board.SIZE - move.getTo().getFirst());
        return algebra;
    }
    
    public static void inputUCI() {
        System.out.println("id name "+ ENGINE_NAME);
        System.out.println("id author Andrea");
        System.out.println("uciok");
    }
    public static void inputSetOption(String inputString) {
        //set options
    }
    public static void inputIsReady() {
         System.out.println("readyok");
    }
    public static void inputUCINewGame() {
        //add code here
    }
    public static void inputPosition(String input) {
        board = new Board();
        turn = ColourEnum.WHITE;
        input=input.substring(9).concat(" ");
        if (input.contains("startpos ")) {
            input=input.substring(9);
            board.initialize();
        }
        if (input.contains("moves")) {
            input=input.substring(input.indexOf("moves")+6);
            while (input.length() > 0)
            {
                Move move = algebraToMove(input);
                board.getGrid()[move.getFrom().getFirst()][move.getFrom().getSecond()].move(move.getTo());
                input=input.substring(input.indexOf(' ')+1);
                turn = oppositeTurn(turn);
            }
        }
    }
    public static void inputGo() {
        Move move = search.bestMove(board, turn);
        turn = oppositeTurn(turn);
        System.out.println(move);
        board.getGrid()[move.getFrom().getFirst()][move.getFrom().getSecond()].move(move.getTo());
        
        System.out.println("bestmove " + moveToAlgebra(move));
    }
    public static void inputPrint() {
        System.out.println(board.toString());
    }
    
}
