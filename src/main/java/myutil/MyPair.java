package myutil;

/**
 * Pair implementation (Standard Template Library style)
 * @author Andrea Galvan
 * @version 1.0
 */
public class MyPair <T1, T2> {
    private T1 first;
    private T2 second;

    /**
     * Main constructor
     * @param first first pair element
     * @param second second pair element
     */
    public MyPair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    /**
     * Default constructor
     */
    public MyPair(){
        first = null;
        second = null;
    }
    
    //getter/setter
    public T1 getFirst(){
        return first;
    }
    public T2 getSecond(){
        return second;
    }
    public void setFirst(T1 first){
        this.first = first;
    }
    public void setSecond(T2 second){
        this.second = second;
    }

    //other methods
    @Override
    public String toString(){
        return "First: " + first.toString() + 
               "; Second: " + second.toString();
    }
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
        MyPair<T1, T2> pair;

        try{
            pair = (MyPair<T1, T2>)o;
        }
        catch(ClassCastException e){
            return false;
        }
        
        if(first.equals(pair.getFirst()) && second.equals(pair.getSecond()))
            return true;
        
        return false;
    }
    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }
}