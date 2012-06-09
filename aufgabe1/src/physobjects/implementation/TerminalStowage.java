/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import Values.interfaces.Mass;
import Values.interfaces.StowageLocation;
import java.util.Collection;
import java.util.Set;
import physobjects.interfaces.Container;
import physobjects.interfaces.ContainerStowage;
import physobjects.interfaces.Stowage;

/**
 *
 * @author Sven
 */
final class TerminalStowage extends AbstractBody implements ContainerStowage {
    private Stowage<Container> containerStowage;
    
    private TerminalStowage(int bays,int rows,int tiers){
        containerStowage = Physobjects.containerStowage(bays, rows, tiers);
        boundingBox = Values.boundingBoxInM(100, 100, 15);
    }
    
    public static ContainerStowage createTerminalStowage(int bays,int rows,int tiers){
    return new TerminalStowage(bays,rows,tiers);
    }
    
    @Override
    public Mass mass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BoundingBox getBoundingBox() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mass emptyMass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mass maxMass() {
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
    public void load(int bayNo, int rowNo, Container elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void load(Container elem) {
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
    public Container get(StowageLocation stowLoc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Container> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StowageLocation locationOf(Container elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
