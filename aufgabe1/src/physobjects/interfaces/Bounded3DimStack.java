/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.interfaces;

import Values.interfaces.StowageLocation;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author abg667
 */
public interface Bounded3DimStack<E> extends Mutable {
    
    
    
    void load(int bayNo,int rowNo, E elem);
    void load(E elem);
    //void loadAll(Collection<?> extends E coll);
    
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
     
}
