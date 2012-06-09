/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import physobjects.interfaces.Container;
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
abstract class AbstractContainer extends AbstractBody implements Container {
   
    protected Stowage<Pallet> palletStowage ; //SOLANGE HIER NIX DRIN HÄNGT NULLPOINTER
    protected Mass emptyMass = Values.ZERO_MASS;
    protected Mass maxMass = Values.ZERO_MASS;
    protected final UniqueID uID = Values.uniqueID();
    protected StowageLocation loc = Values.ZERO_STOWAGELOC;
    protected Container StowageReference;
    

    
    @Override
    public Mass mass() {
//        for (int i = 0; i < palletStowage.; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                for (int k = 0; k < 10; k++) {
//                    
//                }
//                
//            }
//            
//        }
//        return Values.ZERO_MASS;
        return palletStowage.mass().add(emptyMass());
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
        return palletStowage.isEmpty();
        //return (mass().equals(emptyMass()));
    }

    @Override
    public boolean isFull() {
        return palletStowage.isFull();
    }


    @Override
    public void load(int bayNo, int rowNo, Pallet elem) {
        mass = mass.add(elem.mass());
        palletStowage.load(bayNo, rowNo, elem);
    }

    @Override
    public void load(Pallet elem) {
        mass = mass.add(elem.mass());
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
        Pallet pallet = palletStowage.get(stowLoc);
        mass = mass.sub(pallet.mass());
        return pallet;
    }

    @Override
    public Set<Pallet> getAll() {
        mass = mass.sub(palletStowage.mass());
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
