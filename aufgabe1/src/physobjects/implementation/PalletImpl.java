/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;
import Values.implementation.Values;
/**
 *
 * @author abg667
 */
final class PalletImpl extends AbstractPallet {

    private PalletImpl(){
        mass = Values.massInKg(50);
        boundingBox = Values.boundingBoxInM(1.20,0.80, 1.0);
    }
 
    @Override
    public boolean isFree() {
        return false;
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
