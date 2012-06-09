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
    
    
    private ContainerImpl(){
       emptyMass = Values.massInKg(2250);
       maxMass = Values.massInKg(24000);
       boundingBox = Values.boundingBoxInM(6.058,2.438,2.591);
       palletStowage = Physobjects.palletStowage();
    }
    
    static Container createContainer(){
        return new ContainerImpl();
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
    
}
