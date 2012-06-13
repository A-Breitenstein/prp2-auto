/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import java.util.Collection;
import physobjects.interfaces.Pallet;

/**
 *
 * @author abg667
 */
final class NullContainer extends AbstractContainer{

    private NullContainer(){}
    static NullContainer nullContainer(){
        return new NullContainer();
    }
    
    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public String print() {
        return Physobjects.NULLCONTAINERSYM;
    }
    
}
