/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.interfaces;

import Values.interfaces.Mass;

/**
 *
 * @author abg667
 */
public interface WithCargo {
    
    Mass emptyMass();
    Mass maxMass();
    boolean isEmpty();
    boolean isFull();
    
}
