/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.interfaces;

import Values.interfaces.Mass;
import Values.interfaces.StowageLocation;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author abg667
 */
public interface Bounded3DimStack<E> extends Mutable {
    
    
    //TODO:boolean?
    boolean load(int bayNo,int rowNo, E elem);
    boolean load(E elem);
//    boolean loadAll(Collection<?> extends E coll);
//    void load(int bayNo,int rowNo, E elem);
//    void load(E elem);
    boolean load(int bayNo,int rowNo,int tierNo, E elem);
    //boolean loadAll(Collection<?> extends E coll);
    //TODO: void loadAll(Collection<? extends E>  coll);
    void loadAll(Collection<? extends E>  coll);
    
    //Prädikate
    boolean isEmpty();
    boolean isFull();
    boolean tierIsEmpty(int bay,int row);
    boolean tierIsFull(int bay, int row);
    boolean contains(Object elem);
    boolean containsAll(Collection<?> coll);
    
    //Accessors and Properties
    E get(StowageLocation stowLoc);
    Set<E> getAll();
    StowageLocation locationOf(E elem);
    void printStack();
    
     
}
