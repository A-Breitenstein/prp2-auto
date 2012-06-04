/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import physobjects.interfaces.Bounded3DimStack;
import Values.interfaces.StowageLocation;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author abg667
 */
public class Bounded3DimStackImpl<E> implements Bounded3DimStack<E> {

    @Override
    public void load(int bayNo, int rowNo, E elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void load(E elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Object elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E get(StowageLocation stowLoc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<E> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StowageLocation locationOf(E elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
