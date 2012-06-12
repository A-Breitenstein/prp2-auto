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
import physobjects.interfaces.Container;
import physobjects.interfaces.Pallet;



/**
 *
 * @author abg667
 */
abstract class AbstractPallet extends AbstractBody implements Pallet {
    protected StowageLocation loc = Values.ZERO_STOWAGELOC;
    protected Container StowageReference;
    final protected UniqueID uID = Values.uniqueID();
    protected Mass mass = Values.ZERO_MASS;
    
    @Override
    public Mass mass() {
        return mass;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
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
    public int compareTo(Pallet o) {
        return this.uniqueID().compareTo(o.uniqueID());
    }
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName()+": id: "+uniqueID();
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Container)) return false;
        return this.compareTo(((Pallet)o)) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.uID != null ? this.uID.hashCode() : 0);
        return hash;
    }
    
    
    
  
}
