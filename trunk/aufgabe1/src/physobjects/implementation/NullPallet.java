/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;


/**
 *
 * @author abg667
 */
final class NullPallet extends AbstractPallet {

   
    private NullPallet(){}
    public static NullPallet nullPallet(){
        return new NullPallet();
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


    
}
