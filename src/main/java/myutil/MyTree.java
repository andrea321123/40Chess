package myutil;

import java.util.LinkedList;

/**
 * Implementantion of a generic tree
 * @author Andrea Galvan
 * @version 1.0
 */
public class MyTree <T> {
    private T info;
    private LinkedList<MyTree<T>> children;

    /**
     * Main constructor
     * @param info info of the root node
     */
    public MyTree(T info){
        this.info  = info;
        children = new LinkedList<>();
    }

    /**
     * Default constructor
     */
    public MyTree(){
        info = null;
        children = new LinkedList<>();
    }

    //getter/setter
    public T getInfo(){
        return info;
    }
    public LinkedList<MyTree<T>> getChildren(){
        return children;
    }
    public void setInfo(T info){
        this.info = info;
    }

    /**
     * Add a new child to the children list
     * @param child child to add
     */
    public void addChild(MyTree<T> child){
        children.add(child);
    }

    /**
     * Remove the child (parameter) from the children list, if exist
     * @param child child to remove
     */
    public void removeChild(MyTree<T> child){
        children.remove(child);
    }
}