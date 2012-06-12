/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;
import Values.implementation.Values;
import physobjects.interfaces.Pallet;
/**
 *
 * @author abg667
 */
final class PalletImpl extends AbstractPallet {

    private PalletImpl(){
        mass = Values.massInKg(50);
        boundingBox = Values.boundingBoxInM(1.20,0.80, 1.0);
    }
    static Pallet pallet(){
        return new PalletImpl();
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

    @Override
    public String print() {
        return Physobjects.PALLETSYM;
    }

    
}
