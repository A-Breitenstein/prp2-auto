/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

/**
 *
 * @author abg667
 */
public class NonPallet extends AbstractPallet{

    private NonPallet(){}
    public static NonPallet nonPallet(){
        return new NonPallet();
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

    
}
