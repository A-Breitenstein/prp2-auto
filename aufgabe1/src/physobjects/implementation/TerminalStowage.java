/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import Values.interfaces.Mass;
import Values.interfaces.StowageLocation;
import java.util.ArrayList;
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
        
        for(int i = 0;i < bays;i++){
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < tiers; k++) {
                    containerStowage.load(i, j, Physobjects.nullContainer());    
                }
            }
          }  
        
    }

    
    public static ContainerStowage createTerminalStowage(int bays,int rows,int tiers){
        return new TerminalStowage(bays,rows,tiers);
    }
    
    @Override
    public Mass mass() {
        return containerStowage.mass();
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public Mass emptyMass() {
        return Values.ZERO_MASS;
    }

    @Override
    public Mass maxMass() {
        return Values.ZERO_MASS;
    }

    @Override
    public boolean isEmpty() {
        return containerStowage.isEmpty();
    }

    @Override
    public boolean isFull() {
        return containerStowage.isFull();
    }

    @Override
    public boolean load(int bayNo, int rowNo, Container elem) {
        return containerStowage.load(bayNo, rowNo, elem);
    }

    @Override
    public boolean load(Container elem) {
       return containerStowage.load(elem);
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        return containerStowage.isEmpty();
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        return containerStowage.tierIsFull(bay, row);
    }

    @Override
    public boolean contains(Object elem) {
        return containerStowage.contains(elem);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Container get(StowageLocation stowLoc) {
        Container temp =  containerStowage.get(stowLoc);
        return temp; 
        
    }

    @Override
    public Set<Container> getAll() {
       return containerStowage.getAll();
    }

    @Override
    public StowageLocation locationOf(Container elem) {
        return containerStowage.locationOf(elem);
    }
// TODO: ?
    @Override
    public boolean loadAll(Collection<? extends Container> coll) {
        return containerStowage.loadAll(coll);
    }
    
    public static void main(String[] args) {
        ContainerStowage terminal = Physobjects.containerTerminal(10, 5, 5);
        Container a = Physobjects.container();
        terminal.load(0,0,Physobjects.container());
        terminal.load(0,0,a);
        terminal.contains(a);
        System.out.println(terminal.isEmpty());
        System.out.println(terminal.isFull());
        System.out.println(terminal.mass());
        System.out.println(terminal.contains(a));
        Container nullconti = terminal.get(Values.stowageLocation(1, 0, 0));
        System.out.println(terminal.contains(nullconti));
        
        
        //System.out.println(terminal.load(3,4, 3, Physobjects.container()));
        
    }

    @Override
    public boolean load(int bayNo, int rowNo, int tierNo, Container elem) {
        return containerStowage.load(bayNo, rowNo, tierNo, elem);
    }

    @Override
    public void printStack() {
        containerStowage.printStack();
    }
}
