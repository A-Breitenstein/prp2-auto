/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import physobjects.interfaces.Container;

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
