/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import Values.interfaces.Mass;
import Values.interfaces.StowageLocation;
import Values.interfaces.UniqueID;
import java.util.Collection;
import java.util.Set;
import physobjects.interfaces.Container;
import physobjects.interfaces.Pallet;
import physobjects.interfaces.Stowage;

/**
 *
 * @author abg667
 */
final class ContainerImpl extends AbstractContainer  {

    private Stowage<Pallet> palletStowage;
    private Mass emptyMass = Values.massInKg(2250);
    private Mass maxMass = Values.massInKg(24000);
    private BoundingBox boundingBox = Values.boundingBoxInM(6.058,2.438,2.591);
    private final UniqueID uID = Values.uniqueID();
    private StowageLocation loc = Values.ZERO_STOWAGELOC;
    private Container StowageReference;
    
    private ContainerImpl(int bay, int row, int tier){
       emptyMass = Values.massInKg(2250);
       maxMass = Values.massInKg(24000);
       boundingBox = Values.boundingBoxInM(6.058,2.438,2.591);
    }
    
    @Override
    public Mass mass() {
        for (int i = 0; i < palletStowage.   length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < 10; k++) {
                    
                }
                
            }
            
        }
        return Values.ZERO_MASS;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public Mass emptyMass() {
        return emptyMass; 
    }

    @Override
    public Mass maxMass() {
        return maxMass;
    }

    @Override
    public boolean isEmpty() {
        return (mass().equals(emptyMass()));
    }

    @Override
    public boolean isFull() {
        return palletStowage.isFull();
    }

    @Override
    public boolean isFree() {
      return  false;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public void load(int bayNo, int rowNo, Pallet elem) {
        palletStowage.load(bayNo, rowNo, elem);
    }

    @Override
    public void load(Pallet elem) {
        palletStowage.load(elem);
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        return palletStowage.tierIsEmpty(bay, row);
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        return palletStowage.tierIsEmpty(bay, row);
    }

    @Override
    public boolean contains(Object elem) {
        return palletStowage.contains(elem);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pallet get(StowageLocation stowLoc) {
        return palletStowage.get(stowLoc);
    }

    @Override
    public Set<Pallet> getAll() {
        return palletStowage.getAll();
    }

    @Override
    public StowageLocation locationOf(Pallet elem) {
        return palletStowage.locationOf(elem);
    }

    @Override
    public UniqueID uniqueID() {
        return uID;
    }

    @Override
    public StowageLocation loc() {
        return loc;
    }

    @Override
    public void setLocNull() {
        loc = Values.ZERO_STOWAGELOC;
    }

    @Override
    public void setLoc(Container stowage, StowageLocation loc) {
        this.loc = loc; 
        this.StowageReference = stowage;
    }

    @Override
    public int compareTo(Container o) {
        return uniqueID().compareTo(o.uniqueID());
    }
    
}
