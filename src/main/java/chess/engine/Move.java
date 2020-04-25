package chess.engine;

import myutil.MyPair;

/**
 * Implements a chess move
 * 
 * @author Andrea Galvan
 * @version 1.1
 */
public class Move {
    private MyPair<Integer, Integer> from;
    private MyPair<Integer, Integer> to;

    /**
     * Main constructor
     * @param from position of the piece we want to move
     * @param to final position of the piece
     */
    public Move(MyPair<Integer, Integer> from, MyPair<Integer, Integer> to){
        this.from = from;
        this.to = to;
    }

    /**
     * Default constructor
     */
    public Move(){
        from = new MyPair<Integer, Integer>();
        to = new MyPair<Integer, Integer>();
    }

    //getter/setter
    public MyPair<Integer, Integer> getFrom(){
        return from;
    }
    public MyPair<Integer, Integer> getTo(){
        return to;
    }
    public void setFrom(MyPair<Integer, Integer> from){
        this.from = from;
    }
    public void setTo(MyPair<Integer, Integer> to){
        this.to = to;
    }

    @Override
    public String toString(){
        return "From: " + from.toString() + "To: " + to.toString() + "\n";
    }
}