/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import java.util.Collection;
import physobjects.interfaces.Pallet;

/**
 *
 * @author Sven
 */
public class NonContainer extends AbstractContainer{

    private NonContainer(){}
    public static NonContainer nonContainer(){
        return new NonContainer();
    }
    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isBlocked() {
        return true;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
//TODO: ?
    @Override
    public void loadAll(Collection<? extends Pallet> coll) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
