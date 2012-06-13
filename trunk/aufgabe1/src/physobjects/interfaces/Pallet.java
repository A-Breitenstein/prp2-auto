/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.interfaces;

import Values.interfaces.Immutable;


/**
 *
 * @author abg667
 */
public interface Pallet extends Immutable, Body, WithUniqueID, WithStowLoc<Stowage<Pallet>>, WithForm, Comparable<Pallet> {
    
}
