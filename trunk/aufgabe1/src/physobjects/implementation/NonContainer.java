/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

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
}
